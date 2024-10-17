package com.meli.freemarket.features.products.presentation.detail.events

import com.meli.freemarket.features.products.presentation.detail.model.ProductDetail

sealed class ProductDetailUiStates {
    data class DisplayProductDetailUiState(val productDetail: ProductDetail) :
        ProductDetailUiStates()

    object LoadingUiState : ProductDetailUiStates()
    object ErrorUiState : ProductDetailUiStates()
    object DefaultUiState : ProductDetailUiStates()
}