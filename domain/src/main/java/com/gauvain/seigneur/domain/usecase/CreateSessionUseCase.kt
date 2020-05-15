package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.ErrorType
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.UserSessionModel
import com.gauvain.seigneur.domain.provider.CreateSessionProvider

interface CreateSessionUseCase {
    suspend fun invoke(userId:String, userPassword:String): Outcome<UserSessionModel, ErrorType>

    companion object {
        fun create(provider: CreateSessionProvider): CreateSessionUseCase =
            CreateSessionUseCaseImpl(provider)
    }
}