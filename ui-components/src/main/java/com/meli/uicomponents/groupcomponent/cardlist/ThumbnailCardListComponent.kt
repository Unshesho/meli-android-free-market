package com.meli.uicomponents.groupcomponent.cardlist

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.meli.uicomponents.components.cards.AttrsThumbnailCard
import com.meli.uicomponents.databinding.UiGroupcomponentThumbnailCardListBinding
import com.meli.uicomponents.groupcomponent.cardlist.adapter.ThumbnailCardListAdapter

data class AttrsThumbnailCardListComponent(
    val productList: List<AttrsThumbnailCard>,
    val onClick: (String?) -> Unit = {}
)

class ThumbnailCardListComponent @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiGroupcomponentThumbnailCardListBinding? = null

    init {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = UiGroupcomponentThumbnailCardListBinding.inflate(inflater, this)
    }

    fun setAttributes(attrs: AttrsThumbnailCardListComponent) {
        setAdapter(attrs)
    }

    private fun setAdapter(attrs: AttrsThumbnailCardListComponent) = binding?.apply {
        uiComponentThumbnailCardListRecyclerView.adapter =
            ThumbnailCardListAdapter(attrs.productList, attrs.onClick)
    }
}
