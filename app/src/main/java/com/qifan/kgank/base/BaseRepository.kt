package com.qifan.kgank.base

import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume

/**
 * Created by Qifan on 12/03/2019.
 */

open class BaseRepository {

    suspend fun <T : Any> executeForResponse(request: suspend () -> Response<T>): BaseResult<T> {
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
//
//    /**
//     * Suspend extension that allows suspend [Deferred] inside coroutine.
//     *
//     * @return sealed class [Result] object that can be
//     *         casted to [Result.Success] (success) or [Result.Error] (HTTP error)
//     *         and [Result.Exception] (other errors)
//     */
//    suspend fun <T : Any> Deferred<Response<T>>.awaitResult(
//        scope: CoroutineScope
//    ): BaseResult<T> {
//        return suspendCancellableCoroutine { continuation ->
//            scope.launch(Dispatchers.IO) {
//                try {
//                    val response = await()
//                    continuation.resume(
//                        if (response.isSuccessful) {
//                            val body = response.body()
//                            if (body == null) {
//                                BaseResult.Exception(NullPointerException("Response body is null"))
//                            } else {
//                                BaseResult.Success(body, response = response.raw())
//                            }
//                        } else {
//                            BaseResult.Error(HttpException(response), response = response.raw())
//                        }
//                    )
//                } catch (e: Throwable) {
//                    continuation.resume(BaseResult.Exception(e))
//                }
//                registerOnCompletion(continuation)
//            }
//        }
//    }
//
//    private fun Deferred<*>.registerOnCompletion(continuation: CancellableContinuation<*>) {
//        continuation.invokeOnCancellation {
//            if (continuation.isCancelled)
//                try {
//                    cancel()
//                } catch (ex: Throwable) {
//                    //Ignore cancel exception
//                    ex.printStackTrace()
//                }
//        }
//    }

}