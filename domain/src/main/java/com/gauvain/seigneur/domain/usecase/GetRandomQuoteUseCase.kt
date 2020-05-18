package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.ErrorType
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.provider.GetRandomQuoteProvider

interface GetRandomQuoteUseCase {
    suspend fun invoke(): Outcome<QuoteModel, ErrorType>

    companion object {
        fun create(provider: GetRandomQuoteProvider): GetRandomQuoteUseCase =
            GetRandomQuoteUseCaseImpl(provider)
    }
}