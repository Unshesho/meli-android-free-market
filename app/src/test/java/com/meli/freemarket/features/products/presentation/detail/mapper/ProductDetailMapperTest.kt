package com.meli.freemarket.features.products.presentation.detail.mapper

import com.meli.freemarket.features.products.RemoteProductsFactory.makeRemoteProduct
import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductDetailMapperTest {
    private val mapper = ProductDetailMapper()

    @Test
    fun `given RemoteProduct when toProductDetail then return ProductDetail`() = runBlocking {
        val remoteProduct = makeRemoteProduct(3)
        val productDetail = with(mapper) { remoteProduct.toProductDetail() }

        assertEquals(remoteProduct.name, productDetail.name)
        assertEquals(remoteProduct.picture?.first()?.pictureUrl, productDetail.imageUrl)
        assertEquals(remoteProduct.price, productDetail.price)
        assertEquals(remoteProduct.installments?.rate, productDetail.rate)
        assertEquals(
            remoteProduct.characteristics?.first()?.characteristic,
            productDetail.characteristic.first().characteristic
        )
        assertEquals(
            remoteProduct.characteristics?.first()?.characteristicDescription,
            productDetail.characteristic.first().characteristicDescription
        )
    }

    @Test
    fun `given RemoteProduct with null values when toProductDetail then return ProductDetail`() =
        runBlocking {
            val remoteProduct = RemoteProduct()
            val productDetail = with(mapper) { remoteProduct.toProductDetail() }

            assertTrue(productDetail.name.isEmpty())
            assertTrue(productDetail.price.isEmpty())
            assertTrue(productDetail.rate == 0.0f)
            assertTrue(productDetail.imageUrl.isEmpty())
            assertTrue(productDetail.characteristic.isEmpty())
        }
}