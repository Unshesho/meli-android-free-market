package com.meli.freemarket.features.products.list.presentation.events

import com.meli.freemarket.features.products.list.presentation.model.ProductList

sealed class ProductUiStates {
    data class DisplayProductListUiState(val productList: ProductList) : ProductUiStates()
    data object DefaultUiState : ProductUiStates()
    data object LoadingUiState : ProductUiStates()
    data class ErrorUiState(val error: Throwable) : ProductUiStates()
}
