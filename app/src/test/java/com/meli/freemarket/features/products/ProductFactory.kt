package com.meli.freemarket.features.products

import com.meli.freemarket.features.products.presentation.list.model.Characteristic
import com.meli.freemarket.features.products.presentation.list.model.Product
import com.meli.freemarket.features.products.presentation.list.model.ProductList
import com.meli.utils.testingtools.randomfactory.RandomFactory

object ProductFactory {
    fun makeProductList(count: Int) = ProductList(
        products = makeListProduct(count)
    )

    private fun makeListProduct(count: Int) = (1..count).map { makeProduct() }

    fun makeProduct() = Product(
        id = RandomFactory.generateRandomString(),
        name = RandomFactory.generateRandomString(),
        image = RandomFactory.generateRandomString(),
        price = RandomFactory.generateRandomString(),
        rate = RandomFactory.generateRandomFloat()
    )
}