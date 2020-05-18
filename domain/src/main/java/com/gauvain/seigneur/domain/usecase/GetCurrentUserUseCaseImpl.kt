package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.*
import com.gauvain.seigneur.domain.provider.GetUserException
import com.gauvain.seigneur.domain.provider.GetUserProvider

internal class GetCurrentUserUseCaseImpl(private val provider: GetUserProvider) :
    GetCurrentUserUseCase {

    override suspend fun invoke(login: String): Outcome<CurrentUserModel, ErrorType> {
        return try {
            val result = provider.get(login)
            Outcome.Success(result)
        } catch (e: GetUserException) {
            handleException(e)
        }
    }

    private fun handleException(e: GetUserException): Outcome.Error<ErrorType> =
        when (e.type) {
            RequestExceptionType.UNKNOWN_HOST -> Outcome.Error(ErrorType.ERROR_UNKNOWN_HOST)
            RequestExceptionType.CONNECTION_LOST -> Outcome.Error(ErrorType.ERROR_CONNECTION_LOST)
            RequestExceptionType.UNAUTHORIZED -> Outcome.Error(ErrorType.ERROR_UNAUTHORIZED)
            RequestExceptionType.SERVER_INTERNAL_ERROR -> Outcome.Error(ErrorType.ERROR_INTERNAL_SERVER)
            else -> Outcome.Error(ErrorType.ERROR_UNKNOWN)
        }
}