package com.meli.freemarket.features.products.list.data.remote

import com.meli.freemarket.features.products.list.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.list.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.list.data.remote.retrofit.ProductListWebService
import com.meli.freemarket.features.products.list.data.source.ProductListRemote

class ProductListRemoteImpl(private val api: ProductListWebService) : ProductListRemote {
    override suspend fun getProductList(product: String): RemoteProductList =
        api.getProductList(product)

    override suspend fun getProductDetail(productId: String): RemoteProduct =
        api.getProductDetail(productId)
}
