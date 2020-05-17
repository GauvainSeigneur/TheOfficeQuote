package com.gauvain.seigneur.theofficequote.model

import com.gauvain.seigneur.data_adapter.model.QuoteItemEntity
import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.utils.StringPresenter

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

fun QuoteItemEntity.toData(): QuoteItemData =
    QuoteItemData(
        id = this.id,
        body = StringPresenter(R.string.quote_placeholder, this.body),
        author = StringPresenter(R.string.author_placeholder, this.author)
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

