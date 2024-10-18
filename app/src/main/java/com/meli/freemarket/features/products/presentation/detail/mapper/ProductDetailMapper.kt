package com.meli.freemarket.features.products.presentation.detail.mapper

import com.meli.freemarket.features.products.data.remote.model.RemoteCharacteristic
import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.presentation.detail.model.Characteristic
import com.meli.freemarket.features.products.presentation.detail.model.ProductDetail

class ProductDetailMapper {
    fun RemoteProduct.toProductDetail() = ProductDetail(
        name = name.orEmpty(),
        price = price.orEmpty(),
        rate = installments?.rate ?: 0.0f,
        imageUrl = picture?.first()?.pictureUrl.orEmpty(),
        characteristic = characteristics?.map { it.toCharacteristic() } ?: emptyList()
    )


    private fun RemoteCharacteristic.toCharacteristic() = Characteristic(
        characteristic = characteristic.orEmpty(),
        characteristicDescription = characteristicDescription.orEmpty()
    )
}
