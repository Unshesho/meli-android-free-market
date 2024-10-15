package com.meli.freemarket.features.products.list.presentation.model

data class Product(
    val id: String,
    val name: String,
    val image: String,
    val price: String,
    val installments: Installments,
    val characteristic: List<Characteristic>
)
