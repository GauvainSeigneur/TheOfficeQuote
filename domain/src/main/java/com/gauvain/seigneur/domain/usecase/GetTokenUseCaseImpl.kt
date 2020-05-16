package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.*
import com.gauvain.seigneur.domain.provider.GetTokenException
import com.gauvain.seigneur.domain.provider.GetTokenProvider

internal class GetTokenUseCaseImpl(val provider: GetTokenProvider) :
    GetTokenUseCase {

    override suspend fun invoke(): Outcome<String, ErrorType> {
        return try {
            val result = provider.getToken()
            Outcome.Success(result)
        } catch (e: GetTokenException) {
            Outcome.Error(ErrorType.ERROR_UNKNOWN)
        }
    }
}