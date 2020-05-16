package com.gauvain.seigneur.theofficequote.injection

import com.gauvain.seigneur.domain.usecase.CreateSessionUseCase
import com.gauvain.seigneur.domain.usecase.GetTokenUseCase
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCase
import com.gauvain.seigneur.domain.usecase.InsertTokenUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { CreateSessionUseCase.create(get()) }
    single { InsertTokenUseCase.create(get()) }
    single { GetTokenUseCase.create(get()) }
    single { GetUserFavoriteQuotesUseCase.create(get()) }
}