package com.meli.uicomponents.components.template

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.meli.uicomponents.databinding.UiTemplateErrorBinding


data class AttrsErrorTemplate(
    val title: String? = null,
    val description: String? = null,
    val textButton: String? = null,
    val onClick: () -> Unit = {}
)

class ErrorTemplate @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiTemplateErrorBinding? = null

    init {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = UiTemplateErrorBinding.inflate(inflater, this)
    }

    fun setAttributes(attrs: AttrsErrorTemplate) {
        setTexts(attrs)
        setListener(attrs)
    }

    private fun setTexts(attrs: AttrsErrorTemplate) = binding?.apply {
        errorTemplateTitle.text = attrs.title
        errorTemplateErrorDescription.text = attrs.description
        errorTemplateButton.text = attrs.textButton
    }

    private fun setListener(attrs: AttrsErrorTemplate) = binding?.apply {
        errorTemplateButton.setOnClickListener {
            attrs.onClick.invoke()
        }
    }
}
