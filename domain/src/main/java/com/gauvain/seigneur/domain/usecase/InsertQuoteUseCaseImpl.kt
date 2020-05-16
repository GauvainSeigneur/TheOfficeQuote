package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.*
import com.gauvain.seigneur.domain.provider.InsertQuoteItemException
import com.gauvain.seigneur.domain.provider.InsertQuoteItemProvider

internal class InsertQuoteUseCaseImpl(private val provider: InsertQuoteItemProvider) :
    InsertQuoteUseCase {

    override suspend fun invoke(model: QuoteModel): Outcome<Long, ErrorType> {
        return try {
            val result = provider.insert(model)
            Outcome.Success(result)
        } catch (e: InsertQuoteItemException) {
            Outcome.Error(ErrorType.ERROR_UNKNOWN)
        }
    }
}