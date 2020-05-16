package com.gauvain.seigneur.data_adapter.adapter

import com.gauvain.seigneur.data_adapter.model.*
import com.gauvain.seigneur.data_adapter.service.FavQuoteService
import com.gauvain.seigneur.domain.model.QuotesModel
import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.provider.GetQuotesException
import com.gauvain.seigneur.domain.provider.GetQuotesProvider
import retrofit2.Response

class GetQuotesAdapter(private val service: FavQuoteService) :
    GetQuotesProvider {

    override fun get(filter: String, type: String, page: Int): QuotesModel {
        val result = runCatching {
            service.getQuotes(filter, type, page).execute()
        }.onFailure {
            throw GetQuotesException(RequestExceptionType.UNKNOWN_HOST, "message")
        }
        return handleResult(result)
    }

    private fun handleResult(result: Result<Response<Quotes>>): QuotesModel {
        return result.run {
            getOrNull()?.body().let { session ->
                if (session?.errorCode != null && session.errorCode != 0) {
                    throw GetQuotesException(RequestExceptionType.UNAUTHORIZED, session.message)
                } else {
                    session?.toDomain()
                } ?: throw GetQuotesException(RequestExceptionType.BODY_NULL, "Body null")
            }
        }
    }
}