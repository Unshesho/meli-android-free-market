package com.meli.freemarket.di.poc.feature1.di

import com.meli.freemarket.di.poc.feature1.Feature1Activity
import com.meli.freemarket.di.poc.feature1.data.KoinRepository
import com.meli.freemarket.di.poc.feature1.presentation.KoinViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val feature1Module = module {
    scope<Feature1Activity> {
        scoped { KoinRepository() }
        viewModel { KoinViewModel(get()) }
    }
}
