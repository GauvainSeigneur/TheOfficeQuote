package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.*
import com.gauvain.seigneur.domain.provider.CreateSessionException
import com.gauvain.seigneur.domain.provider.CreateSessionProvider

internal class CreateSessionUseCaseImpl(private val provider: CreateSessionProvider) :
    CreateSessionUseCase {

    override suspend fun invoke(userId:String, userPassword:String): Outcome<UserSessionModel, ErrorType> {
        return try {
            val result = provider.createSession(userId, userPassword)
            Outcome.Success(result)
        } catch (e: CreateSessionException) {
            handleException(e)
        }
    }

    private fun handleException(e: CreateSessionException): Outcome.Error<ErrorType> =
        when (e.type) {
            RequestExceptionType.UNKNOWN_HOST -> Outcome.Error(ErrorType.ERROR_UNKNOWN_HOST)
            RequestExceptionType.CONNECTION_LOST -> Outcome.Error(ErrorType.ERROR_CONNECTION_LOST)
            RequestExceptionType.UNAUTHORIZED -> Outcome.Error(ErrorType.ERROR_UNAUTHORIZED)
            RequestExceptionType.SERVER_INTERNAL_ERROR -> Outcome.Error(ErrorType.ERROR_INTERNAL_SERVER)
            else -> Outcome.Error(ErrorType.ERROR_UNKNOWN)
        }
}