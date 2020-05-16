package com.gauvain.seigneur.theofficequote.injection

import com.gauvain.seigneur.theofficequote.view.login.LogInViewModel
import com.gauvain.seigneur.theofficequote.view.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LogInViewModel(
            get(),
            get()
        )

        SplashViewModel(get())
    }
}