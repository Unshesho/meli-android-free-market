package com.meli.freemarket.features.products.data.remote

import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.data.remote.retrofit.ProductWebService
import com.meli.freemarket.features.products.data.source.ProductRemote

class ProductRemoteImpl(private val api: ProductWebService) : ProductRemote {
    override suspend fun getProductList(product: String): RemoteProductList =
        api.getProductList(product)

    override suspend fun getProductDetail(productId: String): RemoteProduct =
        api.getProductDetail(productId)
}
