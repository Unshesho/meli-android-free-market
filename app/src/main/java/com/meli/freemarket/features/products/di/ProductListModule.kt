package com.meli.freemarket.features.products.di

import com.meli.freemarket.features.products.presentation.ProductListViewModel
import com.meli.freemarket.features.products.presentation.list.mapper.ProductListMapper
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val productListModule = module {
    single { ProductListMapper() }
    single { Dispatchers.IO }
    viewModel {
        ProductListViewModel(
            repository = get(),
            mapper = get(),
            dispatcher = get()
        )
    }
}
