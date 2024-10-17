package com.meli.freemarket.features.products.presentation.list.mapper

import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.presentation.list.model.Product
import com.meli.freemarket.features.products.presentation.list.model.ProductList

class ProductListMapper {
    fun RemoteProductList.toProductList() = ProductList(
        products = products?.map { it.toProduct() } ?: emptyList()
    )

    private fun RemoteProduct.toProduct() = Product(
        id = id.orEmpty(),
        name = name.orEmpty(),
        image = image.orEmpty(),
        price = price.orEmpty(),
        rate = installments?.rate ?: 0.0f
    )
}