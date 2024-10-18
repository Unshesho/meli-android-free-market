package com.meli.freemarket.features.products.ui.navigation

import android.view.View
import com.meli.freemarket.features.products.ui.list.ProductListFragmentDirections
import com.meli.utils.navigation.safeNavigation

class ProductNavigator {

    fun goToProductDetail(view: View?, productId: String, productRate: Float) = view?.apply {
        val direction =
            ProductListFragmentDirections.fromProductListToDetail(productId, productRate)
        safeNavigation(view, direction)
    }
}