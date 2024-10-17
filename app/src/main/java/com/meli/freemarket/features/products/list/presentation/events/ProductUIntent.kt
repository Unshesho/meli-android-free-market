package com.meli.freemarket.features.products.list.presentation.events

sealed class ProductUIntent {
    data class SearchProductUIntent(val product: String) : ProductUIntent()
    data class RefreshUIntent(val product: String) : ProductUIntent()
}