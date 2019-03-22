package com.qifan.kgank.di

import com.qifan.kgank.data.source.remote.KGankRepository
import org.koin.dsl.module.module

/**
 * Created by Qifan on 16/03/2019.
 */

val repoModule = module {
    factory<KGankRepository> { KGankRepository(get()) }
}