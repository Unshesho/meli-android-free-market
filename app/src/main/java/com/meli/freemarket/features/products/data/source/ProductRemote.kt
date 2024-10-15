package com.meli.freemarket.features.products.data.source

import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList

interface ProductRemote {
    suspend fun getProductList(product: String): RemoteProductList
    suspend fun getProductDetail(productId: String): RemoteProduct
}
