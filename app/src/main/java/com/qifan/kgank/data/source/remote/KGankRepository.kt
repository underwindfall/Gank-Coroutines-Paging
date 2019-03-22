package com.qifan.kgank.data.source.remote

import com.qifan.kgank.api.KGankService
import com.qifan.kgank.base.BaseRepository
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Qifan on 15/02/2019.
 */

class KGankRepository(private val remoteService: KGankService) : BaseRepository() {
    private var dataSourceFactory: KGankListRemoteDataSourceFactory? = null

    fun getDataSourceFactory(scope: CoroutineScope): KGankListRemoteDataSourceFactory {
        return dataSourceFactory ?: KGankListRemoteDataSourceFactory(remoteService, scope)
    }

    companion object {
        const val DEF_PAGE_SIZE = 20
    }
}