package com.qifan.kgank.data.source.remote

import com.qifan.kgank.api.KGankService
import com.qifan.kgank.base.BaseListRemoteDataSource
import com.qifan.kgank.model.KGankResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

/**
 * Created by Qifan on 20/03/2019.
 */
class KGankListRemoteDataSource(
    private val service: KGankService,
    private val scope: CoroutineScope,
    private val type: String
) : BaseListRemoteDataSource<Int, KGankResultsItem>(scope) {
    val status = netWorkState
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, KGankResultsItem>) {
        loadData(apiCall = { service.gankAsync(type, 1, params.requestedLoadSize).await() }) { entity ->
            entity.results?.let { callback.onResult(it, 1, 2) }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, KGankResultsItem>) {
        loadData(apiCall = { service.gankAsync(type, params.key, params.requestedLoadSize).await() }) { entity ->
            entity.results?.let {
                callback.onResult(it, params.key + 1)
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.coroutineContext.cancel()
    }

}