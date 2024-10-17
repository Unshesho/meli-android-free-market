package com.meli.freemarket.features.products.data.remote.retrofit

import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductWebService {
    @GET("search")
    suspend fun getProductList(
        @Query("q") product: String
    ): RemoteProductList

    @GET("items/{product-id}")
    suspend fun getProductDetail(
        @Path("product-id") productId: String
    ): RemoteProduct
}