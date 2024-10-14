package com.meli.freemarket.features.products.list.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteProductList(
    @SerializedName("results") val products: List<RemoteProductList>? = null
)
