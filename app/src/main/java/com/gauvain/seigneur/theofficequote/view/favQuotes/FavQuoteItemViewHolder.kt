package com.gauvain.seigneur.theofficequote.view.favQuotes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.model.QuoteItemData
import kotlinx.android.synthetic.main.item_quotes.view.*

class FavQuoteItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        val layout = R.layout.item_quotes
    }

    fun bind(itemData: QuoteItemData?) {
        with(itemView) {
            quoteTextView.text = itemData?.body
            authorTextView.text = itemData?.author
        }
    }

}