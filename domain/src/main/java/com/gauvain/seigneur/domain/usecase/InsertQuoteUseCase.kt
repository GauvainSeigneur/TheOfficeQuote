package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.ErrorType
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.model.UserSessionModel
import com.gauvain.seigneur.domain.provider.CreateSessionProvider
import com.gauvain.seigneur.domain.provider.InsertQuoteItemProvider
import com.gauvain.seigneur.domain.provider.InsertTokenProvider

interface InsertQuoteUseCase {
    suspend fun invoke(model: QuoteModel): Outcome<Long, ErrorType>

    companion object {
        fun create(provider: InsertQuoteItemProvider): InsertQuoteUseCase =
            InsertQuoteUseCaseImpl(provider)
    }
}