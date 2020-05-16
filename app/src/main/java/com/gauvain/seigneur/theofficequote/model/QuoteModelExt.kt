package com.gauvain.seigneur.theofficequote.model

import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.model.QuotesModel

fun QuotesModel.toData():QuotesData = QuotesData(
    quotes = this.quoteList.map {
        getQuoteItemData(it)
    }
)

private fun getQuoteItemData(model: QuoteModel): QuoteItemData =
    QuoteItemData(
        model.id,
        model.body,
        model.author
    )


