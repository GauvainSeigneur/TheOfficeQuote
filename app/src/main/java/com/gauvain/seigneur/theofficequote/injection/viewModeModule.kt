package com.gauvain.seigneur.theofficequote.injection

import com.gauvain.seigneur.theofficequote.LogInViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LogInViewModel(get()) }
}