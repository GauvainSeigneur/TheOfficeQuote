package com.gauvain.seigneur.theofficequote.model

import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.model.QuotesModel

fun QuotesModel.toData():QuotesData = QuotesData(
    quotes = this.quoteList.map {
        it.toData()
    }
)

fun QuoteModel.toData(): QuoteItemData =
    QuoteItemData(
        id = this.id,
        body = this.body,
        author = this.author
    )

fun QuoteModel.toDetailsData(): QuoteDetailsData =
    QuoteDetailsData(
        id = this.id,
        isDialog = this.isDialog,
        isPrivate = this.isPrivate,
        tags = this.tags,
        url = this.url,
        favoritesCount = this.favoritesCount,
        upvotesCount = this.upvotesCount,
        downvotesCount = this.downvotesCount,
        body = this.body,
        author = this.author,
        authorPermalink = this.authorPermalink
    )



