package com.gauvain.seigneur.data_adapter.model

import com.gauvain.seigneur.domain.model.QuoteModel

fun QuoteOfDay.toDomain(): QuoteModel =
    QuoteModel(
        id = this.quote.id,
        isDialog = this.quote.isDialog,
        isPrivate = this.quote.isPrivate,
        tags = this.quote.tags?: emptyList(),
        url = this.quote.url?:"No Url provided",
        favoritesCount =  this.quote.favoritesCount,
        upvotesCount = this.quote.upvotesCount,
        downvotesCount = this.quote.downvotesCount,
        author = this.quote.author?:"Not defined",
        authorPermalink = this.quote.authorPermalink?:"No permalink",
        body = this.quote.body
    )