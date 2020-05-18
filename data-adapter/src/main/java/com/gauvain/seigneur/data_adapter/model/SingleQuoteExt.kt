package com.gauvain.seigneur.data_adapter.model

import com.gauvain.seigneur.domain.model.QuoteModel

fun SingleQuote.toDomain(): QuoteModel =
    QuoteModel(
        id = this.id,
        isDialog = this.isDialog,
        isPrivate = this.isPrivate,
        tags = this.tags?: emptyList(),
        url = this.url?:"No Url provided",
        favoritesCount =  this.favoritesCount,
        upvotesCount = this.upvotesCount,
        downvotesCount = this.downvotesCount,
        author = this.author?:"Not defined",
        authorPermalink = this.authorPermalink?:"No permalink",
        body = this.body
    )