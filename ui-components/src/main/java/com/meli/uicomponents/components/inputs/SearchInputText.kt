package com.meli.uicomponents.components.inputs

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import com.meli.uicomponents.databinding.ComponentSearchInputTextBinding

data class AttrsSearchInputText(
    val hint: String,
    val onSearch: () -> Unit
)

class SearchInputText @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: ComponentSearchInputTextBinding? = null

    init {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ComponentSearchInputTextBinding.inflate(inflater, this)
    }

    fun setAttributes(attrs: AttrsSearchInputText) {
        setHint(attrs)
        setSearchBehavior(attrs)
    }

    private fun setHint(attrs: AttrsSearchInputText) = binding?.apply {
        componentSearchInputText.queryHint = attrs.hint
    }

    private fun setSearchBehavior(attrs: AttrsSearchInputText) = binding?.apply {
        componentSearchInputText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                attrs.onSearch.invoke()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }
}
