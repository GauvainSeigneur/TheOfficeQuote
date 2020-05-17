package com.gauvain.seigneur.theofficequote.injection

import com.gauvain.seigneur.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { CreateSessionUseCase.create(get()) }
    single { InsertTokenUseCase.create(get()) }
    single { GetTokenUseCase.create(get()) }
    single { GetUserFavoriteQuotesUseCase.create(get()) }
    single { InsertQuoteUseCase.create(get()) }
    single { GetCurrentUserUseCase.create(get()) }
}