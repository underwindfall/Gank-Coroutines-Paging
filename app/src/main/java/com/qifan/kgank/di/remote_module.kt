package com.qifan.kgank.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.qifan.kgank.api.KGankService
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Qifan on 16/03/2019.
 */

val remoteModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://gank.io/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single<KGankService> {
        get<Retrofit>().create(KGankService::class.java)
    }
}