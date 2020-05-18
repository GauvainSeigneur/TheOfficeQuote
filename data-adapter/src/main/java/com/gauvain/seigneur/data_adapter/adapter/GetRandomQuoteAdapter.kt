package com.gauvain.seigneur.data_adapter.adapter

import com.gauvain.seigneur.data_adapter.model.*
import com.gauvain.seigneur.data_adapter.service.FavQuoteService
import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.provider.GetRandomQuoteException
import com.gauvain.seigneur.domain.provider.GetRandomQuoteProvider
import retrofit2.Response

class GetRandomQuoteAdapter(private val service: FavQuoteService) :
    GetRandomQuoteProvider {

    override fun getRandom(): QuoteModel {
        val result = runCatching {
            service.getRandom().execute()
        }.onFailure {
            throw GetRandomQuoteException(RequestExceptionType.UNKNOWN_HOST, it.message)
        }
        return handleResult(result)
    }

    private fun handleResult(result: Result<Response<SingleQuote>>): QuoteModel {
        return result.run {
            getOrNull()?.body().let { quote ->
                    if (quote?.errorCode != null && quote.errorCode != 0) {
                        throw GetRandomQuoteException(RequestExceptionType.UNAUTHORIZED, quote.message)
                    } else {

                        quote?.toDomain()
                }?:throw GetRandomQuoteException(RequestExceptionType.BODY_NULL, "Body null")
            }
        }
    }


}