package com.meli.freemarket.features.products.list.presentation

import com.meli.freemarket.features.products.ProductFactory.makeProductList
import com.meli.freemarket.features.products.RemoteProductsFactory.makeRemoteProductList
import com.meli.freemarket.features.products.data.ProductRepository
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.presentation.ProductListViewModel
import com.meli.freemarket.features.products.presentation.list.events.ProductUIntent.SearchProductUIntent
import com.meli.freemarket.features.products.presentation.list.events.ProductUiStates.DefaultUiState
import com.meli.freemarket.features.products.presentation.list.events.ProductUiStates.DisplayProductListUiState
import com.meli.freemarket.features.products.presentation.list.events.ProductUiStates.ErrorUiState
import com.meli.freemarket.features.products.presentation.list.mapper.ProductListMapper
import com.meli.freemarket.features.products.presentation.list.model.ProductList
import com.meli.utils.testingtools.randomfactory.RandomFactory.generateRandomString
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProductListViewModelTest {
    private val repository = mockk<ProductRepository>()
    private val mapper = mockk<ProductListMapper>()
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private lateinit var viewModel: ProductListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProductListViewModel(
            repository = repository,
            mapper = mapper,
            dispatcher = testDispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given SearchProductUIntent when subscribeAndProcessUserIntents then emit DisplayProductListUiState`() =
        testScope.runTest {
            val product = generateRandomString()
            val intent = SearchProductUIntent(product)
            val remoteProductList = makeRemoteProductList(3)
            val productList = makeProductList(3)

            stubGetProductList(remoteProductList = remoteProductList, product = product)
            stubMapper(remoteProductList, productList)

            viewModel.subscribeAndProcessUserIntents(userIntents = flow { emit(intent) })

            val result = viewModel.uiStates().take(2).toList()

            assertTrue(result.last() is DisplayProductListUiState)
        }

    @Test
    fun `given SearchProductUIntent when subscribeAndProcessUserIntents then emit DefaultUiState`() =
        testScope.runTest {
            val product = generateRandomString()
            val intent = SearchProductUIntent(product)
            val remoteProductList = makeRemoteProductList(3)
            val productList = makeProductList(3)

            stubGetProductList(remoteProductList = remoteProductList, product = product)
            stubMapper(remoteProductList, productList)

            viewModel.subscribeAndProcessUserIntents(userIntents = flow { emit(intent) })

            val result = viewModel.uiStates().take(2).first()

            assertTrue(result is DefaultUiState)
        }

    @Test
    fun `given SearchProductUIntent when subscribeAndProcessUserIntents then emit ErrorUiState`() =
        testScope.runTest {
            val product = generateRandomString()
            val intent = SearchProductUIntent(product)
            val throwable = Throwable()

            stubGetProductListError(product, throwable)

            viewModel.subscribeAndProcessUserIntents(userIntents = flow { emit(intent) })

            val result = viewModel.uiStates().take(2).last()

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
