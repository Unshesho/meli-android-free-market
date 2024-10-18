package com.meli.freemarket.features.products.di

import com.meli.freemarket.features.products.navigation.ProductNavigator
import com.meli.freemarket.features.products.presentation.ProductDetailViewModel
import com.meli.freemarket.features.products.presentation.detail.mapper.ProductDetailMapper
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val productDetailModule = module {
    single { ProductDetailMapper() }
    single { Dispatchers.IO }
    single { ProductNavigator() }
    viewModel {
        ProductDetailViewModel(
            repository = get(),
            mapper = get(),
            dispatcher = get()
        )
    }
}