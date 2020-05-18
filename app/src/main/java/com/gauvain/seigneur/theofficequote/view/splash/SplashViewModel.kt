package com.gauvain.seigneur.theofficequote.view.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.usecase.GetTokenUseCase
import com.gauvain.seigneur.theofficequote.model.ErrorData
import com.gauvain.seigneur.theofficequote.model.ErrorDataType
import com.gauvain.seigneur.theofficequote.model.LiveDataState
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias TokenState = LiveDataState<String>

class SplashViewModel(
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        job.cancel()
    }

    val tokenData: MutableLiveData<TokenState> by lazy {
        getToken()
        MutableLiveData<TokenState>()
    }

    private fun getToken() {
        viewModelScope.launch(Dispatchers.Main) {
            fetchToken()
        }
    }

    private suspend fun fetchToken() {
        val result = withContext(Dispatchers.IO) {
            getTokenUseCase.invoke()
        }

        when (result) {
            is Outcome.Success -> {
                tokenData.value = LiveDataState.Success("Success")
            }
            is Outcome.Error -> {
                tokenData.value = LiveDataState.Error(
                    ErrorData(
                        ErrorDataType.INFORMATIVE
                    )
                )
            }
        }
    }

}