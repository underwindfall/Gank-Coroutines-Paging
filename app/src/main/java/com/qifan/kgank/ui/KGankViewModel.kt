package com.qifan.kgank.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.qifan.kgank.api.NetworkState
import com.qifan.kgank.base.BaseViewModel
import com.qifan.kgank.data.source.remote.KGankRepository
import com.qifan.kgank.model.KGankResultsItem

/**
 * Created by Qifan on 06/03/2019.
 */
class KGankViewModel(repository: KGankRepository) : BaseViewModel() {
    private val categoryName: MutableLiveData<String> = MutableLiveData()
    //paging
    private val gankContentResult = Transformations.map(categoryName) {
        repository.getData(this, it)
    }

    val gankContentList: LiveData<PagedList<KGankResultsItem>> = Transformations.switchMap(gankContentResult) {
        it.pagedList
    }
    val loadStatus: LiveData<NetworkState> = Transformations.switchMap(gankContentResult) {
        it.networkState
    }

    fun setCategory(type: String) {
        categoryName.value = type
    }
//    val gankContentList: LiveData<PagedList<KGankResultsItem>> =
//        repository.getPagedGankListBuilder(scope = this).build()
//
//    val loadStatus: LiveData<NetworkState> = dataSourceFactory.netWorkState
}

