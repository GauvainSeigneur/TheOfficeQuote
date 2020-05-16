package com.gauvain.seigneur.domain.usecase

import com.gauvain.seigneur.domain.model.*
import com.gauvain.seigneur.domain.provider.InsertTokenException
import com.gauvain.seigneur.domain.provider.InsertTokenProvider

internal class InsertTokenUseCaseImpl(private val insertTokenProvider: InsertTokenProvider) :
    InsertTokenUseCase {

    override suspend fun invoke(token: String): Outcome<Long, ErrorType> {
        return try {
            val result = insertTokenProvider.insert(token)
            Outcome.Success(result)
        } catch (e: InsertTokenException) {
            Outcome.Error(ErrorType.ERROR_UNKNOWN)
        }
    }

}