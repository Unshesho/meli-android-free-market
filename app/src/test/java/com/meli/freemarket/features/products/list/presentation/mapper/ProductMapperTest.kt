package com.meli.freemarket.features.products.list.presentation.mapper

import com.meli.freemarket.features.products.RemoteProductsFactory.makeRemoteProductList
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.presentation.list.model.ProductList
import com.meli.freemarket.features.products.presentation.list.mapper.ProductListMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductMapperTest {
    private val mapper = ProductListMapper()

    @Test
    fun `given RemoteProductList when toProductList then return ProductList`() {
        runBlocking {
            val productListSize = 3
            val remoteProductList = makeRemoteProductList(productListSize)
            val productList = with(mapper) { remoteProductList.toProductList() }

            assertMapper(
                remoteProductList = remoteProductList,
                productList = productList
            )
        }
    }

    @Test
    fun `given RemoteProductList with null values when toProductList then return ProductList empty`() {
        runBlocking {
            val remoteProductList = RemoteProductList()
            val productList = with(mapper) { remoteProductList.toProductList() }

            assertTrue(productList.products.isEmpty())
        }
    }

    private fun assertMapper(
        remoteProductList: RemoteProductList,
        productList: ProductList
    ) {
        assertEquals(
            remoteProductList.products?.first()?.id.orEmpty(),
            productList.products.first().id
        )
        assertEquals(
            remoteProductList.products?.first()?.name.orEmpty(),
            productList.products.first().name
        )
        assertEquals(
            remoteProductList.products?.first()?.image.orEmpty(),
            productList.products.first().image
        )
        assertEquals(
            remoteProductList.products?.first()?.id.orEmpty(),
            productList.products.first().id
        )
        assertEquals(
            remoteProductList.products?.first()?.price.orEmpty(),
            productList.products.first().price
        )
    }
}