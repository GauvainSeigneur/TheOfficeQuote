package com.gauvain.seigneur.theofficequote.model

import com.gauvain.seigneur.theofficequote.utils.StringPresenter

data class QuotesData(
    val quotes: List<QuoteItemData>
)

data class QuoteItemData(
    val id: Int,
    val body: StringPresenter,
    val author: StringPresenter
)
