package com.qifan.kgank.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Qifan on 15/03/2019.
 */

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    //data observable

    //ui observable
    private val _snackBar = MutableLiveData<BaseEvent<String>>()
    private val _spinner = MutableLiveData<Boolean>()

    val spinner: LiveData<Boolean> = _spinner
    val snackBar: LiveData<BaseEvent<String>> = _snackBar


//    fun <T : Any> executeCall(
//        callService: suspend () -> BaseResult<T>,
//        handleResult: ((T)) -> Unit
//    ) {
//        viewModelScope.launch {
//            _spinner.value = true
//            _netWorkState.value = NetWorkState(status = NetWorkState.LOADING)
//            val deferred = async(Dispatchers.IO) {
//                callService.invoke()
//            }
//            try {
//                val result = deferred.await().getOrThrow()
//                handleResult(result)
//                _netWorkState.value = NetWorkState(status = NetWorkState.SUCCESS)
//            } catch (exception: Exception) {
//                when (exception) {
//                    is NullPointerException -> _snackBar.value = BaseEvent("Response body is null")
//                    is HttpException -> _snackBar.value = BaseEvent("Http Exception error")
//                    else -> _snackBar.value = BaseEvent("Other Exception")
//                }
//                _netWorkState.value = NetWorkState(status = NetWorkState.ERROR)
//            } finally {
//                _spinner.value = false
//            }
//
//
//        }
//
//
//    }


    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}