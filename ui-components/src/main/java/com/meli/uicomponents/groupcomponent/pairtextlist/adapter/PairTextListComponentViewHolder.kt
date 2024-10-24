package com.meli.uicomponents.groupcomponent.pairtextlist.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.meli.uicomponents.R
import com.meli.uicomponents.components.view.AttrsPairTextView
import com.meli.uicomponents.databinding.UiItemPairTextBinding

class PairTextListComponentViewHolder(private val binding: UiItemPairTextBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(attrsPairTextView: AttrsPairTextView, position: Int) = binding.apply {
        uiItemPairText.setAttributes(attrsPairTextView)

        if (position % 2 == 0) {
            uiItemPairText.setBackgroundColor(binding.root.context.resources.getColor(R.color.ui_sky_blue))
        } else {
            uiItemPairText.setBackgroundColor(Color.WHITE)
        }
    }
}