package com.gauvain.seigneur.data_adapter.injection

import com.gauvain.seigneur.data_adapter.adapter.CreateSessionAdapter
import com.gauvain.seigneur.domain.provider.CreateSessionProvider
import org.koin.dsl.module

val adapterModule = module {
    single<CreateSessionProvider> {
        CreateSessionAdapter(get())
    }
}