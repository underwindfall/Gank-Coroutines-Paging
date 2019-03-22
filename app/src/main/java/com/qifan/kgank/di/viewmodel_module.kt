package com.qifan.kgank.di

import com.qifan.kgank.data.source.remote.KGankRepository
import com.qifan.kgank.ui.KGankViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by Qifan on 16/03/2019.
 */
val viewModelModule = module {
    viewModel { KGankViewModel(get<KGankRepository>()) }
}