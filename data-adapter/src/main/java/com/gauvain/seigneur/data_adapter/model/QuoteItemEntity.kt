package com.gauvain.seigneur.data_adapter.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteItemEntity(
    @PrimaryKey
    val id: Int,
    val isDialog: Boolean,
    val isPrivate: Boolean,
    val tags: List<String>,
    val url: String,
    val favoritesCount: Int,
    val upvotesCount: Int,
    val downvotesCount: Int,
    val author: String,
    val authorPermalink: String,
    val body: String
)
