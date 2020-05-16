package com.gauvain.seigneur.data_adapter.injection

import com.gauvain.seigneur.data_adapter.adapter.CreateSessionAdapter
import com.gauvain.seigneur.data_adapter.adapter.GetQuotesAdapter
import com.gauvain.seigneur.data_adapter.adapter.GetTokenAdapter
import com.gauvain.seigneur.data_adapter.adapter.InsertTokenAdapter
import com.gauvain.seigneur.domain.provider.CreateSessionProvider
import com.gauvain.seigneur.domain.provider.GetQuotesProvider
import com.gauvain.seigneur.domain.provider.GetTokenProvider
import com.gauvain.seigneur.domain.provider.InsertTokenProvider
import org.koin.dsl.module

val adapterModule = module {
    single<CreateSessionProvider> {
        CreateSessionAdapter(get())
    }
    single<InsertTokenProvider> {
        InsertTokenAdapter(get())
    }
    single<GetTokenProvider> {
        GetTokenAdapter(get())
    }
    single<GetQuotesProvider> {
        GetQuotesAdapter(get())
    }
}