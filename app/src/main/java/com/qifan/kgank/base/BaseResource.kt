//package com.qifan.kgank.base
//
//import com.qifan.kgank.network.NetWorkState
//
///**
// * Created by Qifan on 20/03/2019.
// */
//
///**
// * A generic class that holds a value with its loading status.
// * @param <T>
//</T> */
//data class BaseResource<out T>(val status: NetWorkState, val data: T?, val message: String?) {
//    companion object {
//        fun <T> success(data: T?): BaseResource<T> {
//            return BaseResource(NetWorkState.SUCCESS, data, null)
//        }
//
//        fun <T> error(msg: String, data: T?): BaseResource<T> {
//            return BaseResource(NetWorkState.ERROR, data, msg)
//        }
//
//        fun <T> loading(data: T?): BaseResource<T> {
//            return BaseResource(NetWorkState.LOADING, data, null)
//        }
//    }
//}