package com.meli.freemarket.features.products.list.presentation

import com.meli.freemarket.features.products.ProductFactory.makeProductList
import com.meli.freemarket.features.products.RemoteProductsFactory.makeRemoteProductList
import com.meli.freemarket.features.products.data.ProductRepository
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.list.presentation.events.ProductUIntent.SearchProductUIntent
import com.meli.freemarket.features.products.list.presentation.events.ProductUiStates.DisplayProductListUiState
import com.meli.freemarket.features.products.list.presentation.events.ProductUiStates.ErrorUiState
import com.meli.freemarket.features.products.list.presentation.events.ProductUiStates.LoadingUiState
import com.meli.freemarket.features.products.list.presentation.mapper.ProductListMapper
import com.meli.freemarket.features.products.list.presentation.model.ProductList
import com.meli.utils.testingtools.randomfactory.RandomFactory.generateRandomString
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductListViewModelTest {
    private val repository = mockk<ProductRepository>()
    private val mapper = mockk<ProductListMapper>()
    private val viewModel = ProductListViewModel(repository, mapper)

    @Test
    fun `given SearchProductUIntent when subscribeAndProcessUserIntents then emit DisplayProductListUiState`() =
        runBlocking {
            val product = generateRandomString()
            val intent = SearchProductUIntent(product)
            val remoteProductList = makeRemoteProductList(3)
            val productList = makeProductList(3)

            stubGetProductList(remoteProductList = remoteProductList, product = product)
            stubMapper(remoteProductList, productList)

            val result =
                viewModel.subscribeAndProcessUserIntents(userIntents = flow { emit(intent) })
                    .take(2).last()

            assertTrue(result is DisplayProductListUiState)
        }

    @Test
    fun `given SearchProductUIntent when subscribeAndProcessUserIntents then emit LoadingUiState`() =
        runBlocking {
            val product = generateRandomString()
            val intent = SearchProductUIntent(product)
            val remoteProductList = makeRemoteProductList(3)
            val productList = makeProductList(3)

            stubGetProductList(remoteProductList = remoteProductList, product = product)
            stubMapper(remoteProductList, productList)

            val result =
                viewModel.subscribeAndProcessUserIntents(userIntents = flow { emit(intent) })
                    .take(2).first()

            assertTrue(result is LoadingUiState)
        }

    @Test
    fun `given SearchProductUIntent when subscribeAndProcessUserIntents then emit ErrorUiState`() =
        runBlocking {
            val product = generateRandomString()
            val intent = SearchProductUIntent(product)
            val throwable = Throwable()

            stubGetProductListError(product, throwable)

            val result =
                viewModel.subscribeAndProcessUserIntents(userIntents = flow { emit(intent) })
                    .take(2).last()

            assertTrue(result is ErrorUiState)
        }

    private fun stubGetProductList(product: String, remoteProductList: RemoteProductList) {
        coEvery { repository.getProductList(product) } returns flow { emit(remoteProductList) }
    }

    private fun stubGetProductListError(product: String, error: Throwable) {
        coEvery { repository.getProductList(product) } throws error
    }

    private fun stubMapper(remoteProductList: RemoteProductList, productList: ProductList) {
        every { with(mapper) { remoteProductList.toProductList() } } returns productList
    }
}
