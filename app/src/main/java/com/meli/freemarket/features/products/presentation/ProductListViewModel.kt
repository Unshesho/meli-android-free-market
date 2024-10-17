package com.meli.freemarket.features.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.freemarket.features.products.data.ProductRepository
import com.meli.freemarket.features.products.presentation.list.events.ProductUIntent
import com.meli.freemarket.features.products.presentation.list.events.ProductUIntent.RefreshUIntent
import com.meli.freemarket.features.products.presentation.list.events.ProductUIntent.SearchProductUIntent
import com.meli.freemarket.features.products.presentation.list.events.ProductUiStates
import com.meli.freemarket.features.products.presentation.list.events.ProductUiStates.DisplayProductListUiState
import com.meli.freemarket.features.products.presentation.list.events.ProductUiStates.ErrorUiState
import com.meli.freemarket.features.products.presentation.list.events.ProductUiStates.LoadingUiState
import com.meli.freemarket.features.products.presentation.list.mapper.ProductListMapper
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

class ProductListViewModel(
    private val repository: ProductRepository,
    private val mapper: ProductListMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val defaultUiState = ProductUiStates.DefaultUiState
    private val uiState = MutableStateFlow<ProductUiStates>(defaultUiState)

    fun uiStates(): StateFlow<ProductUiStates> = uiState.asStateFlow()

    fun subscribeAndProcessUserIntents(
        userIntents: Flow<ProductUIntent>,
        scope: CoroutineScope = viewModelScope
    ) = userIntents
        .buffer()
        .flatMapMerge { userIntent ->
            userIntent.handleIntent()
        }.onEach {
            uiState.value = it
        }.launchIn(scope)

    private fun ProductUIntent.handleIntent(): Flow<ProductUiStates> =
        when (this) {
            is SearchProductUIntent -> getProductList(product)
            is RefreshUIntent -> getProductList(product)
        }

    private fun getProductList(product: String?) = flow<ProductUiStates> {
        repository.getProductList(product.orEmpty()).collect { remoteProductList ->
            val productList = with(mapper) {
                remoteProductList.toProductList()
            }
            emit(DisplayProductListUiState(productList))
        }
    }.onStart {
        emit(LoadingUiState)
    }.catch { error ->
        emit(ErrorUiState(error))
    }.flowOn(dispatcher)
}
