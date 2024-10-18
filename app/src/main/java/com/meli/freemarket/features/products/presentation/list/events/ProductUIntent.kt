package com.meli.freemarket.features.products.presentation.list.events

sealed class ProductUIntent {
    data class SearchProductUIntent(val product: String) : ProductUIntent()
    data class RefreshUIntent(val product: String) : ProductUIntent()
    data class RetryIntent(val product: String) : ProductUIntent()
}