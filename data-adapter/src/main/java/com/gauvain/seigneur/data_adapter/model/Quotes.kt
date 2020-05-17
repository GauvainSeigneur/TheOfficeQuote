package com.gauvain.seigneur.data_adapter.model

import com.google.gson.annotations.SerializedName

data class Quotes(
    @SerializedName("page")
    val page: Int,
    @SerializedName("last_page")
    val isLastPage:Boolean,
    @SerializedName("quotes")
    val quoteList:List<Quote>,
    //in case of error like api key uncorrect
    @SerializedName("error_code")
    override val errorCode: Int? = null,
    @SerializedName("message")
    override val message: String? = null
):BaseResponse()

data class Quote(
    @SerializedName("id")
    val id: Int,
    @SerializedName("dialogue")
    val isDialog: Boolean,
    @SerializedName("private")
    val isPrivate: Boolean,
    @SerializedName("tags")
    val tags : List<String>?,
    @SerializedName("url")
    val url : String?,
    @SerializedName("favorites_count")
    val favoritesCount : Int,
    @SerializedName("upvotes_count")
    val upvotesCount : Int,
    @SerializedName("downvotes_count")
    val downvotesCount : Int,
    @SerializedName("author")
    val author : String?,
    @SerializedName("author_permalink")
    val authorPermalink : String?,
    @SerializedName("body")
    val body : String
)