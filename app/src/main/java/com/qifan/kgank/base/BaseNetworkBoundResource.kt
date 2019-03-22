//package com.qifan.kgank.base
//
//import androidx.annotation.MainThread
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.qifan.kgank.network.ApiResult
//
///**
// * Created by Qifan on 20/03/2019.
// */
//
//abstract class BaseNetworkBoundResource<ResultType, RequestType>() {
//
//    private val result = MutableLiveData<BaseResource<ResultType>>()
//
//    init {
//        result.value = BaseResource.loading(null)
//        fetchFromNetwork()
//    }
//
//
//    @MainThread
//    private fun setValue(newValue: BaseResource<ResultType>) {
//        if (result.value != newValue) {
//            result.value = newValue
//        }
//    }
//
//    private fun fetchFromNetwork() {
//        val apiResult = createCall().value
//        when (apiResult) {
//            is ApiResult.Success -> result.value()
//            is ApiResult.Error ->
//            is ApiResult.Exception ->
//        }
//    }
//
//    @MainThread
//    protected abstract fun createCall(): LiveData<ApiResult<RequestType>>
//}