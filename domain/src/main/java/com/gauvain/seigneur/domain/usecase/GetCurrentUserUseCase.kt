package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.CurrentUserModel
import com.gauvain.seigneur.domain.model.ErrorType
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.provider.GetUserProvider

interface GetCurrentUserUseCase {
    suspend fun invoke(login:String): Outcome<CurrentUserModel, ErrorType>

    companion object {
        fun create(provider: GetUserProvider): GetCurrentUserUseCase =
            GetCurrentUserUseCaseImpl(provider)
    }
}