package com.gauvain.seigneur.theofficequote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuoteDetailsData(
    val id: Int,
    val isDialog: Boolean,
    val isPrivate: Boolean,
    val tags : List<String>,
    val url : String,
    val favoritesCount : String,
    val upvotesCount : String,
    val downvotesCount : String,
    val author : String,
    val authorPermalink : String,
    val body : String
): Parcelable
