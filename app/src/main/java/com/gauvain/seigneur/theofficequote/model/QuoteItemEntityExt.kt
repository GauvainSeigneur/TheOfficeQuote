package com.gauvain.seigneur.theofficequote.model

import com.gauvain.seigneur.data_adapter.model.QuoteItemEntity
import com.gauvain.seigneur.domain.model.QuoteModel

fun QuoteItemEntity.toModel(): QuoteModel =
    QuoteModel(
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

fun QuoteItemEntity.toDetailsData(): QuoteDetailsData =
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

