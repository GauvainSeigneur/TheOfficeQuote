package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.ErrorType
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.UserSessionModel
import com.gauvain.seigneur.domain.provider.CreateSessionProvider
import com.gauvain.seigneur.domain.provider.InsertTokenProvider

interface InsertTokenUseCase {
    suspend fun invoke(token:String): Outcome<Long, ErrorType>

    companion object {
        fun create(insertTokenProvider: InsertTokenProvider): InsertTokenUseCase =
            InsertTokenUseCaseImpl(insertTokenProvider)
    }
}