package com.meli.freemarket.features.products.list.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteInstallments(
    @SerializedName("rate") val rate: Float? = null

)