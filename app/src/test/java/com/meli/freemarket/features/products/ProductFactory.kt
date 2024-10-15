package com.meli.freemarket.features.products

import com.meli.freemarket.features.products.list.presentation.model.Characteristic
import com.meli.freemarket.features.products.list.presentation.model.Installments
import com.meli.freemarket.features.products.list.presentation.model.Product
import com.meli.freemarket.features.products.list.presentation.model.ProductList
import com.meli.utils.testingtools.randomfactory.RandomFactory

object ProductFactory {
    fun makeProductList(count: Int) = ProductList(
        products = makeListProduct(count)
    )

    fun makeProduct(count: Int) = Product(
        id = RandomFactory.generateRandomString(),
        name = RandomFactory.generateRandomString(),
        image = RandomFactory.generateRandomString(),
        price = RandomFactory.generateRandomString(),
        installments = makeInstallments(),
        characteristic = makeListCharacteristic(count)
    )

    private fun makeListProduct(count: Int) = (1..count).map { makeProduct(count) }

    private fun makeInstallments() = Installments(
        rate = RandomFactory.generateRandomFloat()
    )

    private fun makeListCharacteristic(count: Int) =
        (0..count).map { makeCharacteristic() }

    private fun makeCharacteristic() = Characteristic(
        characteristic = RandomFactory.generateRandomString(),
        characteristicDescription = RandomFactory.generateRandomString()
    )
}