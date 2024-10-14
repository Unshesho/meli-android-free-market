package com.meli.freemarket.features.products.list.data.remote.retrofit

import com.meli.freemarket.features.products.list.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.list.data.remote.model.RemoteProductList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductListWebService {
    @GET("search")
    suspend fun getProductList(
        @Query("product") product: String
    ): RemoteProductList

    @GET("items/{product-id}")
    suspend fun getProductDetail(
        @Path("product-id") productId: String
    ): RemoteProduct
}