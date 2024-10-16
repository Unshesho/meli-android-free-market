package com.meli.uicomponents.groupcomponent.cardlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meli.uicomponents.databinding.UiItemThumbnailCardBinding
import com.meli.uicomponents.groupcomponent.cardlist.AttrsThumbnailCardListComponent

class ThumbnailCardListAdapter(private val attrs: AttrsThumbnailCardListComponent) :
    RecyclerView.Adapter<ThumbnailCardListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ThumbnailCardListViewHolder(
        UiItemThumbnailCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ThumbnailCardListViewHolder, position: Int) {
        holder.bind(attrs, position)
    }

    override fun getItemCount(): Int = attrs.productList.size
}
