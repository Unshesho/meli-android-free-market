package com.meli.uicomponents.components.inputs

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import com.meli.uicomponents.databinding.UiComponentSearchInputTextBinding

data class AttrsSearchInputText(
    val hint: String? = null,
    val searchText: String? = null,
    val onSearch: (String) -> Unit
)

class SearchInputText @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiComponentSearchInputTextBinding? = null

    init {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = UiComponentSearchInputTextBinding.inflate(inflater, this)
    }

    fun setAttributes(attrs: AttrsSearchInputText) {
        setTexts(attrs)
        setSearchBehavior(attrs)
    }

    fun getSearchText(): String? {
        binding?.apply {
            return componentSearchInputText.query.toString()
        }
        return null
    }

    private fun setTexts(attrs: AttrsSearchInputText) = binding?.apply {
        componentSearchInputText.queryHint = attrs.hint
        componentSearchInputText.setQuery(attrs.searchText, false)
    }

    private fun setSearchBehavior(attrs: AttrsSearchInputText) = binding?.apply {
        componentSearchInputText.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                attrs.onSearch.invoke(p0.orEmpty())
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        binding?.componentSearchInputText?.setOnQueryTextListener(null)
    }
}
