package com.qifan.kgank.api

/**
 * Created by Qifan on 16/03/2019.
 * Status of a resource that is provided to the UI.
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
 */

enum class NetworkState {
    LOADING,
    SUCCESS,
    ERROR
}