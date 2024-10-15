package com.meli.freemarket.features.products.list.presentation

import androidx.lifecycle.ViewModel
import com.meli.freemarket.features.products.data.ProductRepository
import com.meli.freemarket.features.products.list.presentation.events.ProductUIntent
import com.meli.freemarket.features.products.list.presentation.events.ProductUIntent.SearchProductUIntent
import com.meli.freemarket.features.products.list.presentation.events.ProductUiStates
import com.meli.freemarket.features.products.list.presentation.events.ProductUiStates.DisplayProductListUiState
import com.meli.freemarket.features.products.list.presentation.events.ProductUiStates.ErrorUiState
import com.meli.freemarket.features.products.list.presentation.events.ProductUiStates.LoadingUiState
import com.meli.freemarket.features.products.list.presentation.mapper.ProductListMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class ProductListViewModel(
    private val repository: ProductRepository,
    private val mapper: ProductListMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    val defaultUiState = LoadingUiState
    private val uiState = MutableStateFlow<ProductUiStates>(defaultUiState)

    fun uiStates(): StateFlow<ProductUiStates> = uiState.asStateFlow()

    fun subscribeAndProcessUserIntents(
        userIntents: Flow<ProductUIntent>
    ) =
        userIntents
            .buffer()
            .flatMapMerge { userIntent ->
                userIntent.handleIntent()
            }


    private fun ProductUIntent.handleIntent(): Flow<ProductUiStates> =
        when (this) {
            is SearchProductUIntent -> getProductList(product)
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
    }.catch {
        emit(ErrorUiState)
    }.flowOn(dispatcher)
}
