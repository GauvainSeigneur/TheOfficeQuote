package com.gauvain.seigneur.theofficequote.model

import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.model.QuotesModel
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.utils.StringPresenter
import java.text.NumberFormat

fun QuotesModel.toData():QuotesData = QuotesData(
    quotes = this.quoteList.map {
        it.toData()
    }
)

fun QuoteModel.toData(): QuoteItemData =
    QuoteItemData(
        id = this.id,
        body = StringPresenter(R.string.quote_placeholder, this.body),
        author = StringPresenter(R.string.author_placeholder, this.author)
    )

fun QuoteModel.toDetailsData(): QuoteDetailsData =
    QuoteDetailsData(
        id = this.id,
        isDialog = this.isDialog,
        isPrivate = this.isPrivate,
        tags = this.tags,
        url = this.url,
        favoritesCount = NumberFormat.getInstance().format(this.favoritesCount),
        upvotesCount = NumberFormat.getInstance().format(this.upvotesCount),
        downvotesCount = NumberFormat.getInstance().format(this.downvotesCount),
        body = this.body,
        author = this.author,
        authorPermalink = this.authorPermalink
    )



