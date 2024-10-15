package com.meli.freemarket.features.products.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteCharacteristic(
    @SerializedName("name") val characteristic: String? = null,
    @SerializedName("value_name") val characteristicDescription: String? = null
)
