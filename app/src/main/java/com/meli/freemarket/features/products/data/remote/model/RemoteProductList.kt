package com.meli.freemarket.features.products.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteProductList(
    @SerializedName("results") val products: List<RemoteProduct>? = null
)
