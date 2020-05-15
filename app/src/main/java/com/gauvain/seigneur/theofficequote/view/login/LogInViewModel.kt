package com.gauvain.seigneur.theofficequote.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.usecase.CreateSessionUseCase
import com.gauvain.seigneur.domain.usecase.InsertTokenUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LogInViewModel(
    private val createSessionUseCase: CreateSessionUseCase,
    private val insertTokenUseCase: InsertTokenUseCase
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
            when (result) {
                is Outcome.Success -> { insertToken(result.data.userToken) }
                is Outcome.Error -> {}
            }
        }
    }

    private fun insertToken(token:String) {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                insertTokenUseCase.invoke(token)
            }
            when (result) {
                is Outcome.Success -> {
                    Log.d("insertToken", "success ${result.data}")
                }
                is Outcome.Error -> {}
            }
        }
    }
}