package com.gauvain.seigneur.theofficequote.view.randomQuote

import androidx.lifecycle.*
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.usecase.GetRandomQuoteUseCase
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.model.*
import com.gauvain.seigneur.theofficequote.utils.StringPresenter
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias quoteInfoState= LiveDataState<QuoteItemData>

class RandomQuoteViewModel(
    private val useCase: GetRandomQuoteUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    val loadingData = MutableLiveData<LoadingState>()

    val randomQuoteInfo: MutableLiveData<quoteInfoState> by lazy {
        getRandom()
        MutableLiveData<quoteInfoState>()
    }

    override fun onCleared() {
        job.cancel()
    }

    fun getRandom() {
        viewModelScope.launch(Dispatchers.Main) {
            loadingData.value = LoadingState.IS_LOADING
            val result = withContext(Dispatchers.IO) {
                useCase.invoke()
            }

            when (result) {
                is Outcome.Success -> {
                    loadingData.value = LoadingState.IS_LOADED
                    randomQuoteInfo.value = LiveDataState.Success(result.data.toData())
                }
                is Outcome.Error -> {
                    randomQuoteInfo.value = LiveDataState.Error(
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