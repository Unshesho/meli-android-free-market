package com.meli.uicomponents.groupcomponent.cardlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.meli.uicomponents.components.cards.AttrsThumbnailCard
import com.meli.uicomponents.databinding.UiItemThumbnailCardBinding
import com.meli.uicomponents.groupcomponent.cardlist.AttrsThumbnailCardListComponent

class ThumbnailCardListViewHolder(private val binding: UiItemThumbnailCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(attrs: AttrsThumbnailCard, onClick: (String?) -> Unit) =
        binding.apply {
            itemThumbnailCard.setAttributes(attrs)
            setListener(attrs, onClick)
        }

    private fun setListener(
        attrs: AttrsThumbnailCard,
        onClick: (String?) -> Unit
    ) =
        binding.apply {
            itemThumbnailCard.setOnClickListener {
                onClick.invoke(attrs.key)
            }
        }
}
