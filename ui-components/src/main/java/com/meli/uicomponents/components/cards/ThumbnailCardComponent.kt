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
import java.text.DecimalFormat

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
        componentThumbnailCardProductName.text = attrs.title
        componentThumbnailCardPrice.text = "$${attrs.price}"
    }

    private fun setRateStars(attrs: AttrsThumbnailCard) = binding?.apply {
        val rate = attrs.rate?.div(10)
        val decimalFormat = DecimalFormat("#.0")
        val formattedRate = decimalFormat.format(rate).toString()
        componentThumbnailCardRate.text = "Rate $formattedRate"
        componentThumbnailCardRatingBar.rating = rate ?: 0.0f
        componentThumbnailCardRatingBar.progressTintList =
            ColorStateList.valueOf(resources.getColor(R.color.ui_primary_blue))
    }

    private fun setThumbnail(attrs: AttrsThumbnailCard) = binding?.apply {
        Picasso.get()
            .load(attrs.imageUrl)
            .placeholder(R.drawable.ui_ic_load)
            .error(R.drawable.ui_ic_image_placeholder)
            .into(componentThumbnailCardImageView)
    }
}
