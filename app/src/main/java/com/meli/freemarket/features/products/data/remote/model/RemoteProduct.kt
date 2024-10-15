package com.meli.freemarket.features.products.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteProduct(
    @SerializedName("id") val id: String? = null,
    @SerializedName("title") val name: String? = null,
    @SerializedName("thumbnail") val image: String? = null,
    @SerializedName("price") val price: String? = null,
    @SerializedName("installments") val installments: RemoteInstallments? = null,
    @SerializedName("attributes") val characteristics: List<RemoteCharacteristic>? = null
)
