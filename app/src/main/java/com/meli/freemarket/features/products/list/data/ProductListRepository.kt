package com.meli.freemarket.features.products.list.data

import com.meli.freemarket.features.products.list.data.remote.model.RemoteProduct
import com.meli.freemarket.features.products.list.data.remote.model.RemoteProductList
import com.meli.freemarket.features.products.list.data.source.ProductListRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductListRepository(
    private val remote: ProductListRemote,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getProductList(product: String): Flow<RemoteProductList> = flow {
        val productList = remote.getProductList(product)
        emit(productList)
    }.flowOn(dispatcher)

    fun getProductDetail(productId: String): Flow<RemoteProduct> = flow {
        val product = remote.getProductDetail(productId)
        emit(product)
    }.flowOn(dispatcher)
}
