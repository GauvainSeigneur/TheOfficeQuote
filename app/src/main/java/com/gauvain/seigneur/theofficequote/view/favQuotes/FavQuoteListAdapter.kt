package com.gauvain.seigneur.theofficequote.view.favQuotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gauvain.seigneur.theofficequote.model.QuoteItemData

class FavQuoteListAdapter(
    private val listener: Listener) : PagedListAdapter<QuoteItemData, FavQuoteItemViewHolder>(DiffCallback) {

    interface Listener {
        fun onClick(
            id:Int?
        )
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<QuoteItemData>() {
            override fun areItemsTheSame(oldItem: QuoteItemData, newItem: QuoteItemData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: QuoteItemData, newItem: QuoteItemData): Boolean {
                return oldItem.body == newItem.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavQuoteItemViewHolder =
        FavQuoteItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                FavQuoteItemViewHolder.layout,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FavQuoteItemViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

}
