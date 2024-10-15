package com.meli.freemarket.features.products.data

import com.meli.freemarket.features.products.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.data.source.ProductRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(
    private val remote: ProductRemote
) {
    fun getProductList(product: String): Flow<RemoteProductList> = flow {
        val productList = remote.getProductList(product)
        emit(productList)
    }

    fun getProductDetail(productId: String): Flow<RemoteProduct> = flow {
        val product = remote.getProductDetail(productId)
        emit(product)
    }
}
