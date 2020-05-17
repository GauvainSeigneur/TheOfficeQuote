package com.gauvain.seigneur.theofficequote.view.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.usecase.CreateSessionUseCase
import com.gauvain.seigneur.domain.usecase.InsertTokenUseCase
import com.gauvain.seigneur.theofficequote.model.ErrorData
import com.gauvain.seigneur.theofficequote.model.ErrorDataType
import com.gauvain.seigneur.theofficequote.model.LiveDataState
import com.gauvain.seigneur.theofficequote.utils.event.Event
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias LoginEventState = Event<LiveDataState<Int>>

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

    val loginEvent = MutableLiveData<LoginEventState>()

    fun login(userId:String, userPassword:String) {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                createSessionUseCase.invoke(userId, userPassword)
            }
            when (result) {
                is Outcome.Success -> { insertToken(result.data.userToken, result.data.login) }
                is Outcome.Error -> {
                    loginEvent.value = Event(LiveDataState.Error(ErrorData(ErrorDataType.INFORMATIVE)))
                }
            }
        }
    }

    private fun insertToken(token:String, login:String) {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                insertTokenUseCase.invoke(token, login)
            }
            when (result) {
                is Outcome.Success -> {
                    loginEvent.value = Event(LiveDataState.Success(0))
                }
                is Outcome.Error -> {
                    loginEvent.value = Event(LiveDataState.Error(ErrorData(ErrorDataType.INFORMATIVE)))
                }
            }
        }
    }
}