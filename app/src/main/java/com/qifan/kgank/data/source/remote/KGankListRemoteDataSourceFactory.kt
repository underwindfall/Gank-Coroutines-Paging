package com.qifan.kgank.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import com.qifan.kgank.api.KGankService
import com.qifan.kgank.api.NetworkState
import com.qifan.kgank.model.KGankResultsItem
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Qifan on 20/03/2019.
 */
class KGankListRemoteDataSourceFactory(
    private val service: KGankService,
    private val scope: CoroutineScope,
    private val type: String
) : DataSource.Factory<Int, KGankResultsItem>() {

    private val postLiveData = MutableLiveData<KGankListRemoteDataSource>()
    val netWorkState: LiveData<NetworkState> = postLiveData.value?.status ?: Transformations.switchMap(postLiveData) {
        it.netWorkState
    }

    override fun create(): DataSource<Int, KGankResultsItem> {
        val dataSource = KGankListRemoteDataSource(service, scope, type)
        postLiveData.postValue(dataSource)
        return dataSource
    }


}