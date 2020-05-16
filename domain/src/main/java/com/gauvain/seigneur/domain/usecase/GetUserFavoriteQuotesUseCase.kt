package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.ErrorType
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.QuotesModel
import com.gauvain.seigneur.domain.provider.GetQuotesProvider

interface GetUserFavoriteQuotesUseCase {
    suspend fun invoke(filter:String, page:Int): Outcome<QuotesModel, ErrorType>

    companion object {
        fun create(provider: GetQuotesProvider): GetUserFavoriteQuotesUseCase =
            GetUserFavoriteQuotesUseCaseImpl(provider)
    }
}