package com.meli.uicomponents.components.view

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.meli.uicomponents.databinding.UiComponentPairTextViewBinding

data class AttrsPairTextView(
    val firstText: String? = null,
    val secondText: String? = null
)

class PairTextView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiComponentPairTextViewBinding? = null

    init {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = UiComponentPairTextViewBinding.inflate(inflater, this)
    }

    fun setAttributes(attrs: AttrsPairTextView) {
        setTexts(attrs)
    }

    private fun setTexts(attrs: AttrsPairTextView) = binding?.apply {
        componentPairTextViewKey.text = attrs.firstText
        componentPairTextViewValue.text = attrs.secondText
    }

}
