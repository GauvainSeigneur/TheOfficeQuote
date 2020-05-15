package com.gauvain.seigneur.theofficequote.view.splash

import androidx.lifecycle.ViewModel
import com.gauvain.seigneur.domain.usecase.GetTokenUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashViewModel(
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        job.cancel()
    }

}