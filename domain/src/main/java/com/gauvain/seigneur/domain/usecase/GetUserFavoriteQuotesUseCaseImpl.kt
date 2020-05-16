package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.*
import com.gauvain.seigneur.domain.provider.GetQuotesException
import com.gauvain.seigneur.domain.provider.GetQuotesProvider

internal class GetUserFavoriteQuotesUseCaseImpl(private val provider: GetQuotesProvider) :
    GetUserFavoriteQuotesUseCase {

    override suspend fun invoke(filter: String, page: Int): Outcome<QuotesModel, ErrorType> {
        return try {
            val result = provider.get(filter, "user", page)
            Outcome.Success(result)
        } catch (e: GetQuotesException) {
            handleException(e)
        }
    }

    private fun handleException(e: GetQuotesException): Outcome.Error<ErrorType> =
        when (e.type) {
            RequestExceptionType.UNKNOWN_HOST -> Outcome.Error(ErrorType.ERROR_UNKNOWN_HOST)
            RequestExceptionType.CONNECTION_LOST -> Outcome.Error(ErrorType.ERROR_CONNECTION_LOST)
            RequestExceptionType.UNAUTHORIZED -> Outcome.Error(ErrorType.ERROR_UNAUTHORIZED)
            RequestExceptionType.SERVER_INTERNAL_ERROR -> Outcome.Error(ErrorType.ERROR_INTERNAL_SERVER)
            else -> Outcome.Error(ErrorType.ERROR_UNKNOWN)
        }
}