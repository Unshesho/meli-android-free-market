package com.meli.freemarket.features.products.list.data

import com.meli.freemarket.features.products.RemoteProductsFactory.makeRemoteProduct
import com.meli.freemarket.features.products.RemoteProductsFactory.makeRemoteProductList
import com.meli.freemarket.features.products.data.ProductRepository
import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.data.source.ProductRemote
import com.meli.utils.testingtools.randomfactory.RandomFactory.generateRandomString
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductListRepositoryTest {
    private val remote = mockk<ProductRemote>()
    private val repository = ProductRepository(remote)

    @Test
    fun `given product, when getProductList, then emit`() = runBlocking {
        val product = generateRandomString()
        val productList = makeRemoteProductList(3)

        stubGetProductList(product, productList)

        val result = repository.getProductList(product).take(2).first()

        assertEquals(result, productList)
    }

    @Test
    fun `given product, when getProductDetail, then emit`() = runBlocking {
        val productId = generateRandomString()
        val product = makeRemoteProduct(3)

        stubGetProductDetail(productId, product)

        val result = repository.getProductDetail(productId).take(2).first()

        assertEquals(result, product)

    }

    private fun stubGetProductList(product: String, productList: RemoteProductList) {
        coEvery { remote.getProductList(product) } returns productList
    }

    private fun stubGetProductDetail(productId: String, product: RemoteProduct) {
        coEvery { remote.getProductDetail(productId) } returns product
    }
}
