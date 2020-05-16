package com.gauvain.seigneur.domain.provider

import com.gauvain.seigneur.domain.model.QuotesModel
import com.gauvain.seigneur.domain.model.RequestExceptionType
import java.lang.Exception

interface GetQuotesProvider {
    @Throws(GetQuotesException::class)
    fun get(filter: String, type: String, page: Int): QuotesModel
}

class GetQuotesException(
    val type: RequestExceptionType,
    val description: String? = null
) : Exception()
