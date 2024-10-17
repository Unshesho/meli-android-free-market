package com.meli.freemarket.features.products.presentation

import com.meli.freemarket.features.products.ProductFactory.makeProductDetail
import com.meli.freemarket.features.products.RemoteProductsFactory.makeRemoteProduct
import com.meli.freemarket.features.products.data.ProductRepository
import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUIntent.SeeProductDetailUIntent
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.DefaultUiState
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.DisplayProductDetailUiState
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.ErrorUiState
import com.meli.freemarket.features.products.presentation.detail.mapper.ProductDetailMapper
import com.meli.freemarket.features.products.presentation.detail.model.ProductDetail
import com.meli.utils.testingtools.randomfactory.RandomFactory.generateRandomString
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
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

class ProductDetailViewModelTest {
    private val repository = mockk<ProductRepository>()
    private val mapper = mockk<ProductDetailMapper>()
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private lateinit var viewModel: ProductDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProductDetailViewModel(
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
    fun `given SeeProductDetailUIntent when subscribeAndProcessUserIntents then emit DisplayProductDetailUiState`() =
        testScope.runTest {
            val productId = generateRandomString()
            val intent = SeeProductDetailUIntent(productId)
            val remoteProduct = makeRemoteProduct(3)
            val productDetail = makeProductDetail(3)

            stubGetProductDetail(productId, remoteProduct)
            stubMapper(remoteProduct, productDetail)

            viewModel.subscribeAndProcessUserIntents(userIntents = flow { emit(intent) })

            val result = viewModel.uiStates().take(2).toList()

            assertTrue(result.last() is DisplayProductDetailUiState)
        }

    @Test
    fun `given SeeProductDetailUIntent when subscribeAndProcessUserIntents then emit DefaultUiState`() =
        testScope.runTest {
            val productId = generateRandomString()
            val intent = SeeProductDetailUIntent(productId)
            val remoteProduct = makeRemoteProduct(3)
            val productDetail = makeProductDetail(3)

            stubGetProductDetail(productId, remoteProduct)
            stubMapper(remoteProduct, productDetail)

            viewModel.subscribeAndProcessUserIntents(userIntents = flow { emit(intent) })

            val result = viewModel.uiStates().take(2).toList()

            assertTrue(result.first() is DefaultUiState)
        }

    @Test
    fun `given SeeProductDetailUIntent when subscribeAndProcessUserIntents then emit ErrorUiState`() =
        testScope.runTest {
            val productId = generateRandomString()
            val intent = SeeProductDetailUIntent(productId)
            val error = Throwable()

            stubGetProductDetailError(productId, error)

            viewModel.subscribeAndProcessUserIntents(userIntents = flow { emit(intent) })

            val result = viewModel.uiStates().take(2).toList()

            assertTrue(result.last() is ErrorUiState)
        }

    private fun stubGetProductDetail(productId: String, remoteProduct: RemoteProduct) {
        coEvery { repository.getProductDetail(productId) } returns flow { emit(remoteProduct) }
    }

    private fun stubGetProductDetailError(productId: String, error: Throwable) {
        coEvery { repository.getProductDetail(productId) } throws error
    }

    private fun stubMapper(remoteProduct: RemoteProduct, productDetail: ProductDetail) {
        every { with(mapper) { remoteProduct.toProductDetail() } } returns productDetail
    }
}
