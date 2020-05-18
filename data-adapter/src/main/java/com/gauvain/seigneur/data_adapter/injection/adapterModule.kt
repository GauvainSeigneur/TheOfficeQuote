package com.gauvain.seigneur.data_adapter.injection

import com.gauvain.seigneur.data_adapter.adapter.*
import com.gauvain.seigneur.domain.provider.*
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
    single<InsertQuoteItemProvider> {
        InsertQuoteItemAdapter(get())
    }
    single<GetUserProvider> {
        GetUserAdapter(get())
    }
    single<GetRandomQuoteProvider> {
        GetRandomQuoteAdapter(get())
    }
}