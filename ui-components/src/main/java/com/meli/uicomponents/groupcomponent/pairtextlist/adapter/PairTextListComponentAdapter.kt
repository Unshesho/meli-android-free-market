package com.meli.uicomponents.groupcomponent.pairtextlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meli.uicomponents.components.view.AttrsPairTextView
import com.meli.uicomponents.databinding.UiItemPairTextBinding

class PairTextListComponentAdapter(private val pairTextList: List<AttrsPairTextView>) :
    RecyclerView.Adapter<PairTextListComponentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = PairTextListComponentViewHolder(
        UiItemPairTextBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PairTextListComponentViewHolder, position: Int) {
        holder.bind(pairTextList[position], position)
    }

    override fun getItemCount(): Int = pairTextList.size

}