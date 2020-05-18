package com.gauvain.seigneur.domain.provider

import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.model.UserSessionModel
import java.lang.Exception

interface GetRandomQuoteProvider {
    @Throws(GetRandomQuoteException::class)
    fun getRandom(): QuoteModel
}

class GetRandomQuoteException(
    val type: RequestExceptionType,
    val description: String? = null
) : Exception()
