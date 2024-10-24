package com.meli.freemarket.features.products.di

import com.meli.freemarket.features.products.data.ProductRepository
import com.meli.freemarket.features.products.data.remote.ProductRemoteImpl
import com.meli.freemarket.features.products.data.remote.retrofit.ProductWebService
import com.meli.freemarket.features.products.data.source.ProductRemote
import com.meli.freemarket.features.products.presentation.ProductDetailViewModel
import com.meli.freemarket.features.products.presentation.ProductListViewModel
import com.meli.freemarket.features.products.presentation.detail.mapper.ProductDetailMapper
import com.meli.freemarket.features.products.presentation.list.mapper.ProductListMapper
import com.meli.freemarket.features.products.ui.navigation.ProductNavigator
import com.meli.network.retrofit.WebServiceFactory
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun productsModule() = module {
    includes(productDataModule(), productDetailModule(), productListModule())
}

private fun productDataModule() = module {
    single<ProductWebService> {
        WebServiceFactory(
            tClass = ProductWebService::class.java
        ).createRemoteWebServiceConfig()
    }
    single<ProductRemote> { ProductRemoteImpl(get()) }
    single { ProductRepository(get()) }
}

private fun productDetailModule() = module {
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

private fun productListModule() = module {
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
