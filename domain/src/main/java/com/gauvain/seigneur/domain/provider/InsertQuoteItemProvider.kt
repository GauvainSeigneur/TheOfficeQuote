package com.gauvain.seigneur.domain.provider

import com.gauvain.seigneur.domain.model.QuoteModel

interface InsertQuoteItemProvider {
    @Throws(InsertQuoteItemException::class)
    suspend fun insert(quoteModel : QuoteModel): Long
}

class InsertQuoteItemException(val description: String? = null) : Exception()
