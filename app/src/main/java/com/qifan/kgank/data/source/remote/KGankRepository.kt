package com.qifan.kgank.data.source.remote

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.qifan.kgank.api.KGankService
import com.qifan.kgank.base.BaseRepository
import com.qifan.kgank.data.source.Listing
import com.qifan.kgank.model.KGankResultsItem
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Qifan on 15/02/2019.
 */

class KGankRepository(private val remoteService: KGankService) : BaseRepository() {
//    private var dataSourceFactory: KGankListRemoteDataSourceFactory? = null
//
//    private fun getDataSourceFactory(scope: CoroutineScope, type: String): KGankListRemoteDataSourceFactory {
//        return dataSourceFactory ?: KGankListRemoteDataSourceFactory(remoteService, scope, type)
//    }

    fun getData(scope: CoroutineScope, type: String): Listing<KGankResultsItem> {
        val dataSourceFactory = KGankListRemoteDataSourceFactory(remoteService, scope, type)
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(KGankRepository.DEF_PAGE_SIZE)
            .setEnablePlaceholders(false)
            .setPageSize(KGankRepository.DEF_PAGE_SIZE)
            .build()
        val pagedList = LivePagedListBuilder(dataSourceFactory, config).build()
        val networkState = dataSourceFactory.netWorkState
        return Listing(
            pagedList,
            networkState
        )
    }

//    fun getPagedGankListBuilder(scope: CoroutineScope, type: String): LiveData<PagedList<KGankResultsItem>> {
//        val config = PagedList.Config.Builder()
//            .setInitialLoadSizeHint(KGankRepository.DEF_PAGE_SIZE)
//            .setEnablePlaceholders(false)
//            .setPageSize(KGankRepository.DEF_PAGE_SIZE)
//            .build()
//        return LivePagedListBuilder(getDataSourceFactory(scope, type), config).build()
//    }
//
//    fun getLoadingStatus(scope: CoroutineScope, type: String): LiveData<NetworkState> {
//        return getDataSourceFactory(scope, type).netWorkState
//    }

    companion object {
        const val DEF_PAGE_SIZE = 20
    }
}