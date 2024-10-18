package com.meli.freemarket.features.products.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePicture(
    @SerializedName("secure_url") val pictureUrl: String? = null
)
