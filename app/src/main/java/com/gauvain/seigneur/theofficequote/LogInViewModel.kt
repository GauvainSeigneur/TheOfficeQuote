package com.gauvain.seigneur.theofficequote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gauvain.seigneur.domain.usecase.CreateSessionUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LogInViewModel(
    private val createSessionUseCase: CreateSessionUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        job.cancel()
    }

    fun login(userId:String, userPassword:String) {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                createSessionUseCase.invoke(userId, userPassword)
            }
        }
    }
}