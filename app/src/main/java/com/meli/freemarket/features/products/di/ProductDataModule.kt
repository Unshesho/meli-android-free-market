package com.meli.freemarket.features.products.di

import com.meli.freemarket.features.products.data.ProductRepository
import com.meli.freemarket.features.products.data.remote.ProductRemoteImpl
import com.meli.freemarket.features.products.data.remote.retrofit.ProductWebService
import com.meli.freemarket.features.products.data.source.ProductRemote
import com.meli.network.retrofit.WebServiceFactory
import org.koin.dsl.module

val productDataModule = module {
    single<ProductWebService> {
        WebServiceFactory(
            tClass = ProductWebService::class.java
        ).createRemoteWebServiceConfig()
    }
    single<ProductRemote> { ProductRemoteImpl(get()) }
    single { ProductRepository(get()) }
}
