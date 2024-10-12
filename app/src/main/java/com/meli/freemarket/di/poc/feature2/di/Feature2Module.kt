package com.meli.freemarket.di.poc.feature2.di

import com.meli.freemarket.di.poc.feature2.data.KoinRepository2
import com.meli.freemarket.di.poc.feature2.presentation.KoinViewModel2
import org.koin.androidx.scope.ScopeActivity
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val feature2Module = module {
    scope<ScopeActivity> {
        scoped { KoinRepository2() }
        viewModel { KoinViewModel2(get()) }
    }
}