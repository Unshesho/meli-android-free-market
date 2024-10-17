package com.meli.freemarket.features.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.freemarket.features.products.data.ProductRepository
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUIntent
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.DefaultUiState
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.DisplayProductDetailUiState
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.ErrorUiState
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.LoadingUiState
import com.meli.freemarket.features.products.presentation.detail.mapper.ProductDetailMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class ProductDetailViewModel(
    private val repository: ProductRepository,
    private val mapper: ProductDetailMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val defaultUiState = DefaultUiState
    private val uiState = MutableStateFlow<ProductDetailUiStates>(defaultUiState)

    fun uiStates(): StateFlow<ProductDetailUiStates> = uiState.asStateFlow()

    fun subscribeAndProcessUserIntents(
        userIntents: Flow<ProductDetailUIntent>,
        scope: CoroutineScope = viewModelScope
    ) = userIntents
        .buffer()
        .flatMapMerge { userIntent ->
            userIntent.handleIntent()
        }.onEach {
            uiState.value = it
        }.launchIn(scope)

    private fun ProductDetailUIntent.handleIntent(): Flow<ProductDetailUiStates> =
        when (this) {
            is ProductDetailUIntent.SearchProductIntent -> getProductDetail(productText)
            is ProductDetailUIntent.SeeProductDetailUIntent -> TODO()
        }

    private fun getProductDetail(productId: String?) = flow<ProductDetailUiStates> {
        repository.getProductDetail(productId.orEmpty()).collect { remoteProductDetail ->
            val productDetail = with(mapper) { remoteProductDetail.toProductDetail() }
            emit(DisplayProductDetailUiState(productDetail))
        }
    }.onStart {
        emit(LoadingUiState)
    }.catch {
        emit(ErrorUiState)
    }.flowOn(dispatcher)
}
