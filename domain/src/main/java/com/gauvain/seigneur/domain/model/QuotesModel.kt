package com.gauvain.seigneur.domain.model

data class QuotesModel(
    val page: Int,
    val isLastPage:Boolean,
    val quoteList:List<QuoteModel>
)

data class QuoteModel(
    val id: Int,
    val isDialog: Boolean,
    val isPrivate: Boolean,
    val tags : List<String>,
    val url : String,
    val favoritesCount : Int,
    val upvotesCount : Int,
    val downvotesCount : Int,
    val author : String,
    val authorPermalink : String,
    val body : String
)
