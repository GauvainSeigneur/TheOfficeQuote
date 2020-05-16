package com.gauvain.seigneur.theofficequote.model

data class QuotesData(
    val quotes: List<QuoteItemData>
)

data class QuoteItemData(
    val id: Int,
    val body: String,
    val author: String
)
