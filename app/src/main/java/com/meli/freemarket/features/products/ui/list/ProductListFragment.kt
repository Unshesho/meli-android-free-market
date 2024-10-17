package com.meli.freemarket.features.products.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.meli.freemarket.databinding.FragmentProductListBinding
import com.meli.freemarket.features.products.ui.ProductsActivity.Companion.SEARCH
import com.meli.freemarket.features.products.presentation.ProductListViewModel
import com.meli.freemarket.features.products.presentation.list.events.ProductUIntent
import com.meli.freemarket.features.products.presentation.list.events.ProductUIntent.RefreshUIntent
import com.meli.freemarket.features.products.presentation.list.events.ProductUIntent.SearchProductUIntent
import com.meli.freemarket.features.products.presentation.list.events.ProductUiStates
import com.meli.freemarket.features.products.presentation.list.events.ProductUiStates.DisplayProductListUiState
import com.meli.freemarket.features.products.presentation.list.events.ProductUiStates.ErrorUiState
import com.meli.freemarket.features.products.presentation.list.model.ProductList
import com.meli.uicomponents.components.cards.AttrsThumbnailCard
import com.meli.uicomponents.groupcomponent.cardlist.AttrsThumbnailCardListComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {
    private var binding: FragmentProductListBinding? = null
    private val viewModel: ProductListViewModel by viewModel()
    private val userIntents: MutableSharedFlow<ProductUIntent> = MutableSharedFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToObserveStates()
        subscribeToProcessIntents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) binding =
            FragmentProductListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    fun refresh(productText: String) {
        emit(RefreshUIntent(productText))
    }

    private fun subscribeToObserveStates() {
        viewModel.run {
            uiStates().onEach { renderUiStates(it) }.launchIn(lifecycleScope)
        }
    }

    private fun subscribeToProcessIntents() {
        viewModel.subscribeAndProcessUserIntents(userIntents())
    }

    private fun userIntents(): Flow<ProductUIntent> = merge(
        searchUserIntent(),
        userIntents.asSharedFlow()
    )

    private fun searchUserIntent() = flow<ProductUIntent> {
        getProductText()?.let { emit(SearchProductUIntent(it)) }
    }

    private fun getProductText() = activity?.intent?.extras?.getString(SEARCH)

    private fun renderUiStates(uiStates: ProductUiStates) {
        hideAll()
        when (uiStates) {
            is DisplayProductListUiState -> showContent(uiStates.productList)
            is ErrorUiState -> {
                Log.d("SHESHO", "eerror: ${uiStates.error}")
            } //TODO: MOSTRAR ERROR
            ProductUiStates.LoadingUiState -> showLoading()
            else -> {}
        }
    }

    private fun showContent(productList: ProductList) {
        setProductList(productList)
    }

    private fun setProductList(productList: ProductList) = binding?.apply {
        fragmentProductListThumbnailList.isVisible = true
        fragmentProductListThumbnailList.setAttributes(
            attrs = AttrsThumbnailCardListComponent(
                productList = productList.products.map { product ->
                    AttrsThumbnailCard(
                        title = product.name,
                        price = product.price,
                        rate = product.rate,
                        imageUrl = product.image
                    )
                }
            )
        )
    }

    private fun showLoading() = binding?.apply {
        fragmentComponentLoader.isVisible = true
    }

    private fun hideAll() = binding?.apply {
        fragmentProductListThumbnailList.isVisible = false
        fragmentComponentLoader.isVisible = false
    }

    private fun emit(intent: ProductUIntent) {
        viewLifecycleOwner.lifecycleScope.launch {
            userIntents.emit(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}