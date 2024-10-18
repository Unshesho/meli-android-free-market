package com.meli.freemarket.features.products.ui.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.meli.freemarket.R
import com.meli.freemarket.databinding.FragmentProductDetailBinding
import com.meli.freemarket.features.products.presentation.ProductDetailViewModel
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUIntent
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUIntent.RetryUIntent
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUIntent.SeeProductDetailUIntent
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.DefaultUiState
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.DisplayProductDetailUiState
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.ErrorUiState
import com.meli.freemarket.features.products.presentation.detail.events.ProductDetailUiStates.LoadingUiState
import com.meli.freemarket.features.products.presentation.detail.model.ProductDetail
import com.meli.uicomponents.components.template.AttrsErrorTemplate
import com.meli.uicomponents.components.view.AttrsPairTextView
import com.meli.uicomponents.groupcomponent.pairtextlist.AttrsPairTextListComponent
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailFragment : Fragment() {
    private var binding: FragmentProductDetailBinding? = null
    private val viewModel: ProductDetailViewModel by viewModel()
    private val userIntents: MutableSharedFlow<ProductDetailUIntent> = MutableSharedFlow()
    private val args: ProductDetailFragmentArgs by navArgs()

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
            FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun subscribeToObserveStates() {
        viewModel.run {
            uiStates().onEach { renderUiStates(it) }.launchIn(lifecycleScope)
        }
    }

    private fun subscribeToProcessIntents() {
        viewModel.subscribeAndProcessUserIntents(userIntents())
    }

    private fun userIntents(): Flow<ProductDetailUIntent> = merge(
        seeProductDetailUIntent(),
        userIntents.asSharedFlow()
    )

    private fun seeProductDetailUIntent() = flow<ProductDetailUIntent> {
        val productId = args.productId
        emit(SeeProductDetailUIntent(productId.orEmpty()))
    }

    private fun renderUiStates(uiStates: ProductDetailUiStates) {
        hideAll()
        when (uiStates) {
            DefaultUiState -> {}
            is DisplayProductDetailUiState -> showContent(uiStates.productDetail)
            ErrorUiState -> showError()
            LoadingUiState -> showLoading()
        }
    }

    private fun showContent(productDetail: ProductDetail) {
        showLayouts()
        setTexts(productDetail)
        setImage(productDetail)
        setRatingBar()
        setCharacteristicList(productDetail)
    }

    private fun showLayouts() = binding?.apply {
        fragmentProductDetailContainer.isVisible = true
        fragmentProductDetailLoader.isVisible = false
    }

    private fun setTexts(productDetail: ProductDetail) = binding?.apply {
        fragmentProductDetailProductName.text = productDetail.name
        fragmentProductDetailPrice.text = "$" + productDetail.price
        fragmentProductDetailCharacteristic.text = getString(R.string.characteristics)
    }

    private fun setImage(productDetail: ProductDetail) = binding?.apply {
        try {
            Picasso.get()
                .load(productDetail.imageUrl)
                .into(fragmentProductDetailImageView)
        } catch (e: Exception) {
            //TODO- Enviar Exception a herramientas de monitoreo de errores como crashlitycs
        }
    }

    private fun setRatingBar() = binding?.apply {
        try {
            val rate = args.productRate.div(10)
            val decimalFormat = DecimalFormat("#.0")
            val formattedRate = decimalFormat.format(rate).toString()
            fragmentProductDetailRate.text = "Rate $formattedRate"
            fragmentProductDetailRatingBar.rating = rate
            fragmentProductDetailRatingBar.progressTintList = ColorStateList.valueOf(
                resources.getColor(
                    com.meli.uicomponents.R.color.ui_primary_blue
                )
            )
        } catch (e: Exception) {
            //TODO- Enviar Exception a herramientas de monitoreo de errores como crashlitycs
        }

    }

    private fun setCharacteristicList(productDetail: ProductDetail) = binding?.apply {
        fragmentProductDetailCharacteristicList.setAttributes(
            attrs = AttrsPairTextListComponent(
                pairTextList = productDetail.characteristic.map { characteristicValues ->
                    AttrsPairTextView(
                        firstText = characteristicValues.characteristic,
                        secondText = characteristicValues.characteristicDescription
                    )
                }
            )
        )
    }

    private fun showError() = binding?.apply {
        hideAll()
        fragmentProductDetailErrorTemplate.isVisible = true
        setErrorTemplate()
    }

    private fun setErrorTemplate() = binding?.apply {
        fragmentProductDetailErrorTemplate.setAttributes(
            attrs = AttrsErrorTemplate(
                title = context?.resources?.getString(R.string.we_have_a_problem),
                description = context?.resources?.getString(R.string.we_are_sorry_try_again),
                textButton = context?.resources?.getString(R.string.retry),
                onClick = { emit(RetryUIntent(args.productId.orEmpty())) }
            )
        )
    }

    private fun showLoading() = binding?.apply {
        fragmentProductDetailLoader.isVisible = true
    }

    private fun hideAll() = binding?.apply {
        fragmentProductDetailLoader.isVisible = false
        fragmentProductDetailContainer.isVisible = false
        fragmentProductDetailErrorTemplate.isVisible = false
    }

    private fun emit(intent: ProductDetailUIntent) {
        viewLifecycleOwner.lifecycleScope.launch {
            userIntents.emit(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
