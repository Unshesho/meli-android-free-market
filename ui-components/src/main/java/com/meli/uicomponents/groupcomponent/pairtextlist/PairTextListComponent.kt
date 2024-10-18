package com.meli.uicomponents.groupcomponent.pairtextlist

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.meli.uicomponents.components.view.AttrsPairTextView
import com.meli.uicomponents.databinding.UiGroupComponentPairTextListBinding
import com.meli.uicomponents.groupcomponent.pairtextlist.adapter.PairTextListComponentAdapter

data class AttrsPairTextListComponent(
    val pairTextList: List<AttrsPairTextView>
)

class PairTextListComponent @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiGroupComponentPairTextListBinding? = null

    init {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = UiGroupComponentPairTextListBinding.inflate(inflater, this)
    }

    fun setAttributes(attrs: AttrsPairTextListComponent) {
        setAdapter(attrs)
    }

    private fun setAdapter(attrsPairTextView: AttrsPairTextListComponent) = binding?.apply {
        uiComponentPairTextRecyclerView.adapter =
            PairTextListComponentAdapter(attrsPairTextView.pairTextList)
    }
}
