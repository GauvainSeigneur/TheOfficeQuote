package com.gauvain.seigneur.theofficequote.injection

import com.gauvain.seigneur.theofficequote.view.favQuotes.FavQuotesViewModel
import com.gauvain.seigneur.theofficequote.view.login.LogInViewModel
import com.gauvain.seigneur.theofficequote.view.splash.SplashViewModel
import com.gauvain.seigneur.theofficequote.view.user.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SplashViewModel(get())
    }

    viewModel {
        LogInViewModel(
            get(),
            get()
        )
    }

    viewModel {
        FavQuotesViewModel(
            get(),
            get(),
            get()
        )
    }

    viewModel {
        UserViewModel(
            get()
        )
    }
}