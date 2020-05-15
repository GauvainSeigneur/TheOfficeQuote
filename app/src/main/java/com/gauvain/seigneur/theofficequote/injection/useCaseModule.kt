package com.gauvain.seigneur.theofficequote.injection

import com.gauvain.seigneur.domain.usecase.CreateSessionUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { CreateSessionUseCase.create(get()) }
}