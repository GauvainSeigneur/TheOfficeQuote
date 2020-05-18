package com.gauvain.seigneur.theofficequote.view.user

import androidx.lifecycle.*
import com.gauvain.seigneur.data_adapter.adapter.GetTokenAdapter
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.usecase.GetCurrentUserUseCase
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.model.*
import com.gauvain.seigneur.theofficequote.utils.StringPresenter
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias UserInfoSate = LiveDataState<UserData>

class UserViewModel(
    private val useCase: GetCurrentUserUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    val loadingData = MutableLiveData<LoadingState>()
    val userInfo: MutableLiveData<UserInfoSate> by lazy {
        getUser()
        MutableLiveData<UserInfoSate>()
    }

    override fun onCleared() {
        job.cancel()
    }

    fun getUser() {
        viewModelScope.launch(Dispatchers.Main) {
            loadingData.value = LoadingState.IS_LOADING
            val result = withContext(Dispatchers.IO) {
                useCase.invoke(GetTokenAdapter.constUserName)
            }

            when (result) {
                is Outcome.Success -> {
                    loadingData.value = LoadingState.IS_LOADED
                    userInfo.value = LiveDataState.Success(result.data.toData())
                }
                is Outcome.Error -> {
                    userInfo.value = LiveDataState.Error(
                        ErrorData(
                            ErrorDataType.RECOVERABLE,
                            StringPresenter(R.string.error_fetch_data_title),
                            StringPresenter(R.string.error_fetch_data_description),
                            StringPresenter(R.string.retry)
                        )
                    )
                }
            }
        }
    }
}