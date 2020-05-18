package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.*
import com.gauvain.seigneur.domain.provider.GetRandomQuoteException
import com.gauvain.seigneur.domain.provider.GetRandomQuoteProvider


internal class GetRandomQuoteUseCaseImpl(private val provider: GetRandomQuoteProvider) :
    GetRandomQuoteUseCase {

    override suspend fun invoke(): Outcome<QuoteModel, ErrorType> {
        return try {
            val result = provider.getRandom()
            Outcome.Success(result)
        } catch (e: GetRandomQuoteException) {
            handleException(e)
        }
    }


    private fun handleException(e: GetRandomQuoteException): Outcome.Error<ErrorType> =
        when (e.type) {
            RequestExceptionType.UNKNOWN_HOST -> Outcome.Error(ErrorType.ERROR_UNKNOWN_HOST)
            RequestExceptionType.CONNECTION_LOST -> Outcome.Error(ErrorType.ERROR_CONNECTION_LOST)
            RequestExceptionType.UNAUTHORIZED -> Outcome.Error(ErrorType.ERROR_UNAUTHORIZED)
            RequestExceptionType.SERVER_INTERNAL_ERROR -> Outcome.Error(ErrorType.ERROR_INTERNAL_SERVER)
            else -> Outcome.Error(ErrorType.ERROR_UNKNOWN)
        }
}