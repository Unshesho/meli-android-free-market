package com.meli.uicomponents.groupcomponent.cardlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meli.uicomponents.components.cards.AttrsThumbnailCard
import com.meli.uicomponents.databinding.UiItemThumbnailCardBinding

class ThumbnailCardListAdapter(
    private val list: List<AttrsThumbnailCard>,
    private val onClick: (String?, Float?) -> Unit = { _, _ -> }
) :
    RecyclerView.Adapter<ThumbnailCardListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ThumbnailCardListViewHolder(
        UiItemThumbnailCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ThumbnailCardListViewHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    override fun getItemCount(): Int = list.size
}
