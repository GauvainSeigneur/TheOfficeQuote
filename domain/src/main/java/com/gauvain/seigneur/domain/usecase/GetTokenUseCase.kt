package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.ErrorType
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.UserSessionModel
import com.gauvain.seigneur.domain.provider.CreateSessionProvider
import com.gauvain.seigneur.domain.provider.GetTokenProvider
import com.gauvain.seigneur.domain.provider.InsertTokenProvider

interface GetTokenUseCase {
    suspend fun invoke(): Outcome<String, ErrorType>

    companion object {
        fun create(provider: GetTokenProvider): GetTokenUseCase =
            GetTokenUseCaseImpl(provider)
    }
}