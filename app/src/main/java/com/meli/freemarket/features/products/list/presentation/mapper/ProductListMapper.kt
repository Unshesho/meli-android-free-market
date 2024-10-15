package com.meli.freemarket.features.products.list.presentation.mapper

import com.meli.freemarket.features.products.data.remote.model.RemoteCharacteristic
import com.meli.freemarket.features.products.data.remote.model.RemoteInstallments
import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.list.presentation.model.Characteristic
import com.meli.freemarket.features.products.list.presentation.model.Installments
import com.meli.freemarket.features.products.list.presentation.model.Product
import com.meli.freemarket.features.products.list.presentation.model.ProductList

class ProductListMapper {
    fun RemoteProductList.toProductList() = ProductList(
        products = products?.map { it.toProduct() } ?: emptyList()
    )

    private fun RemoteProduct.toProduct() = Product(
        id = id.orEmpty(),
        name = name.orEmpty(),
        image = image.orEmpty(),
        price = price.orEmpty(),
        installments = installments.toInstallments(),
        characteristic = characteristics?.map { it.toCharacteristic() } ?: emptyList()
    )

    private fun RemoteInstallments?.toInstallments() = Installments(
        rate = this?.rate ?: 0.0f
    )

    private fun RemoteCharacteristic.toCharacteristic() = Characteristic(
        characteristic = characteristic.orEmpty(),
        characteristicDescription = characteristicDescription.orEmpty()
    )
}