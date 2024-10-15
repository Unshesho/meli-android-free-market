package com.meli.freemarket.features.products.list.data.remote

import com.meli.freemarket.features.products.ProductsFactory.makeRemoteProduct
import com.meli.freemarket.features.products.ProductsFactory.makeRemoteProductList
import com.meli.freemarket.features.products.list.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.list.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.list.data.remote.retrofit.ProductListWebService
import com.meli.utils.testingtools.randomfactory.RandomFactory.generateRandomString
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ProductListRemoteImplTest {
    private val api = mockk<ProductListWebService>()
    private val remote = ProductListRemoteImpl(api)

    @Test
    fun `given product, when getProductList, then return RemoteProductList`() = runBlocking {
        val product = generateRandomString()
        val productList = makeRemoteProductList(3)

        stubGetProductList(product, productList)

        val result = remote.getProductList(product)

        Assert.assertEquals(result, productList)
    }

    @Test
    fun `given productId, when getProductDetail, then return RemoteProduct`() = runBlocking {
        val productId = generateRandomString()
        val product = makeRemoteProduct(3)

        stubGetProductDetail(productId, product)

        val result = remote.getProductDetail(productId)

        Assert.assertEquals(result, product)
    }

    private fun stubGetProductList(product: String, productList: RemoteProductList) {
        coEvery { api.getProductList(product) } returns productList
    }

    private fun stubGetProductDetail(productId: String, product: RemoteProduct) {
        coEvery { api.getProductDetail(productId) } returns product
    }
}
