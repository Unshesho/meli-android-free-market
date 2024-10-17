package com.meli.freemarket.features.products

import com.meli.freemarket.features.products.presentation.detail.model.Characteristic
import com.meli.freemarket.features.products.presentation.detail.model.ProductDetail
import com.meli.freemarket.features.products.presentation.list.model.Product
import com.meli.freemarket.features.products.presentation.list.model.ProductList
import com.meli.utils.testingtools.randomfactory.RandomFactory.generateRandomFloat
import com.meli.utils.testingtools.randomfactory.RandomFactory.generateRandomString

object ProductFactory {
    fun makeProductList(count: Int) = ProductList(
        products = makeListProduct(count)
    )

    private fun makeListProduct(count: Int) = (0..count).map { makeProduct() }

    private fun makeProduct() = Product(
        id = generateRandomString(),
        name = generateRandomString(),
        image = generateRandomString(),
        price = generateRandomString(),
        rate = generateRandomFloat()
    )

    fun makeProductDetail(listCount: Int) = ProductDetail(
        name = generateRandomString(),
        price = generateRandomString(),
        rate = generateRandomFloat(),
        imageUrl = generateRandomString(),
        characteristic = makeCharacteristicList(listCount)
    )

    private fun makeCharacteristicList(count: Int) = (0..count).map { makeCharacteristic() }

    private fun makeCharacteristic() = Characteristic(
        characteristic = generateRandomString(),
        characteristicDescription = generateRandomString()
    )
}