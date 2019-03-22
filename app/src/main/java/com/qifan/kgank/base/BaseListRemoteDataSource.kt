package com.qifan.kgank.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.qifan.kgank.api.NetworkState
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume

/**
 * Created by Qifan on 21/03/2019.
 */

abstract class BaseListRemoteDataSource<K, V>(private val scope: CoroutineScope) : PageKeyedDataSource<K, V>() {
    private val _netWorkState = MutableLiveData<NetworkState>()
    val netWorkState: LiveData<NetworkState> = _netWorkState

    fun <T : Any> loadData(
        apiCall: suspend () -> Response<T>,
        handleResult: (T) -> Unit
    ) {
        scope.launch {
            _netWorkState.value = NetworkState.LOADING
            try {
                val result = async(Dispatchers.IO) {
                    executeForResponse(request = { apiCall.invoke() })
                }
                val finalResult = result.await().getOrThrow()
                _netWorkState.value = NetworkState.SUCCESS
                handleResult(finalResult)
            } catch (exception: Exception) {
                _netWorkState.value = NetworkState.ERROR
            }

        }
    }

    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<K, V>) {
        _netWorkState.postValue(NetworkState.SUCCESS)
    }

    private suspend fun <T : Any> executeForResponse(request: suspend () -> Response<T>): BaseResult<T> {
        return try {
            val response = request.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    BaseResult.Exception(NullPointerException("Response body is null"))
                } else {
                    BaseResult.Success(body, response = response.raw())
                }
            } else {
                BaseResult.Error(HttpException(response), response = response.raw())
            }
        } catch (e: Exception) {
            BaseResult.Exception(e)
        }
    }

    /**
     * Suspend extension that allows suspend [Deferred] inside coroutine.
     *
     * @return sealed class [Result] object that can be
     *         casted to [Result.Success] (success) or [Result.Error] (HTTP error)
     *         and [Result.Exception] (other errors)
     */
    suspend fun <T : Any> Deferred<Response<T>>.awaitResult(): BaseResult<T> {
        return suspendCancellableCoroutine { continuation ->
            scope.launch(Dispatchers.IO) {
                try {
                    val response = await()
                    continuation.resume(
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body == null) {
                                BaseResult.Exception(NullPointerException("Response body is null"))
                            } else {
                                BaseResult.Success(body, response = response.raw())
                            }
                        } else {
                            BaseResult.Error(HttpException(response), response = response.raw())
                        }
                    )
                } catch (e: Throwable) {
                    continuation.resume(BaseResult.Exception(e))
                }
                registerOnCompletion(continuation)
            }
        }
    }

    private fun Deferred<*>.registerOnCompletion(continuation: CancellableContinuation<*>) {
        continuation.invokeOnCancellation {
            if (continuation.isCancelled)
                try {
                    cancel()
                } catch (ex: Throwable) {
                    //Ignore cancel exception
                    ex.printStackTrace()
                }
        }
    }
}