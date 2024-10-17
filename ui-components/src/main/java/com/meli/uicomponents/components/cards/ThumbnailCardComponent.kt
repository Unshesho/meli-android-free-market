package com.meli.uicomponents.components.cards

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.meli.uicomponents.R
import com.meli.uicomponents.databinding.UiComponentThumbnailCardBinding
import com.squareup.picasso.Picasso

data class AttrsThumbnailCard(
    val title: String? = null,
    val rate: Float? = null,
    val price: String? = null,
    val key: String? = null,
    val imageUrl: String? = null
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
        setThumbnail(attrs)
    }

    private fun setTexts(attrs: AttrsThumbnailCard) = binding?.apply {
        val rate = String.format("%.1f", attrs.rate?.div(10))
        componentThumbnailCardProductName.text = attrs.title
        componentThumbnailCardPrice.text = "$${attrs.price}"
        componentThumbnailCardRate.text = "Rate $rate"
    }

    private fun setRateStars(attrs: AttrsThumbnailCard) = binding?.apply {
        val rateStars = attrs.rate?.div(10)
        componentThumbnailCardRatingBar.rating = rateStars ?: 0.0f
        componentThumbnailCardRatingBar.progressTintList =
            ColorStateList.valueOf(resources.getColor(R.color.ui_primary_blue))
    }

    private fun setThumbnail(attrs: AttrsThumbnailCard) = binding?.apply {
        Picasso.get()
            .load(attrs.imageUrl)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ui_ic_load)
            .error(R.drawable.ui_ic_image_placeholder)
            .into(componentThumbnailCardImageView)
    }
}
