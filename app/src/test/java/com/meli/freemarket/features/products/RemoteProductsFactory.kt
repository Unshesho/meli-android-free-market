package com.meli.freemarket.features.products

import com.meli.freemarket.features.products.data.remote.model.RemoteCharacteristic
import com.meli.freemarket.features.products.data.remote.model.RemoteInstallments
import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList
import com.meli.utils.testingtools.randomfactory.RandomFactory.generateRandomFloat
import com.meli.utils.testingtools.randomfactory.RandomFactory.generateRandomString

object RemoteProductsFactory {

    fun makeRemoteProductList(count: Int) = RemoteProductList(
        products = makeListRemoteProduct(count)
    )

    fun makeRemoteProduct(count: Int) = RemoteProduct(
        id = generateRandomString(),
        name = generateRandomString(),
        image = generateRandomString(),
        price = generateRandomString(),
        installments = makeRemoteInstallments(),
        characteristics = makeListRemoteCharacteristic(count)
    )

    private fun makeListRemoteProduct(count: Int) = (1..count).map { makeRemoteProduct(count) }

    private fun makeRemoteInstallments() = RemoteInstallments(
        rate = generateRandomFloat()
    )

    private fun makeListRemoteCharacteristic(count: Int) =
        (0..count).map { makeRemoteCharacteristic() }

    private fun makeRemoteCharacteristic() = RemoteCharacteristic(
        characteristic = generateRandomString(),
        characteristicDescription = generateRandomString()
    )
}
