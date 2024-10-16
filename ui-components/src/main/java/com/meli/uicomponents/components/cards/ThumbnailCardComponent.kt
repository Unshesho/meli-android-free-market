package com.meli.uicomponents.components.cards

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.meli.uicomponents.R
import com.meli.uicomponents.databinding.UiComponentThumbnailCardBinding

data class AttrsThumbnailCard(
    val title: String? = null,
    val rate: Float? = null,
    val price: String? = null,
    val key: String? = null
)

class ThumbnailCardComponent @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiComponentThumbnailCardBinding? = null

    init {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = UiComponentThumbnailCardBinding.inflate(inflater, this)

    }

    fun setAttributes(attrs: AttrsThumbnailCard) {
        setTexts(attrs)
        setRateStars(attrs)
    }

    private fun setTexts(attrs: AttrsThumbnailCard) = binding?.apply {
        val rate = (attrs.rate?.div(10)).toString()
        componentThumbnailCardProductName.text = "$${attrs.price}"
        componentThumbnailCardPrice.text = attrs.price
        componentThumbnailCardRate.text = "Rate $rate"
    }

    private fun setRateStars(attrs: AttrsThumbnailCard) = binding?.apply {
        val rateStars = attrs.rate?.div(10)
        componentThumbnailCardRatingBar.rating = rateStars ?: 0.0f
        componentThumbnailCardRatingBar.progressTintList =
            ColorStateList.valueOf(resources.getColor(R.color.ui_primary_blue))
    }
}
