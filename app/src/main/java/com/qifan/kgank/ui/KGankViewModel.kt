package com.qifan.kgank.ui

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.qifan.kgank.api.NetworkState
import com.qifan.kgank.base.BaseViewModel
import com.qifan.kgank.data.source.remote.KGankRepository
import com.qifan.kgank.model.KGankResultsItem

/**
 * Created by Qifan on 06/03/2019.
 */
class KGankViewModel(repository: KGankRepository) : BaseViewModel(repository) {
    //paging
//    val gankContentList: LiveData<PagedList<KGankResultsItem>> = repository.fetchGankListContent(this)
//    val loadStatus: LiveData<NetworkState> = repository.getLoadingStatus(this)
    private val dataSourceFactory = repository.getDataSourceFactory(this)
    private val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(KGankRepository.DEF_PAGE_SIZE)
        .setEnablePlaceholders(false)
        .setPageSize(KGankRepository.DEF_PAGE_SIZE)
        .build()
    val gankContentList: LiveData<PagedList<KGankResultsItem>> = LivePagedListBuilder(dataSourceFactory, config).build()
    val loadStatus: LiveData<NetworkState> = dataSourceFactory.netWorkState
}

