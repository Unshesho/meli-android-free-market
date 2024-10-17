package com.meli.freemarket.features.products.presentation.detail.model

data class ProductDetail(
    val name: String,
    val price: String,
    val rate: Float,
    val imageUrl: String,
    val characteristic: List<Characteristic>
)

