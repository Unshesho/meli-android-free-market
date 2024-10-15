package com.meli.freemarket.features.products.list.data.source

import com.meli.freemarket.features.products.list.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.list.data.remote.model.RemoteProductList
import retrofit2.Response

interface ProductListRemote {
    suspend fun getProductList(product: String): RemoteProductList
    suspend fun getProductDetail(productId: String):RemoteProduct
}
