package com.meli.freemarket.features.products.presentation.detail.events

sealed class ProductDetailUIntent {
    data class SeeProductDetailUIntent(val productId: String) : ProductDetailUIntent()
}
