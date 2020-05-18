package com.gauvain.seigneur.theofficequote.view.favQuotes

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCase
import com.gauvain.seigneur.domain.usecase.InsertQuoteUseCase
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.model.*
import com.gauvain.seigneur.theofficequote.utils.StringPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuoteDataSource(
    val userName:String,
    val scope: CoroutineScope,
    val useCase: GetUserFavoriteQuotesUseCase,
    val insertQuoteUseCase: InsertQuoteUseCase
) : PageKeyedDataSource<Int, QuoteModel>(){

    private var isLastPage= false

    val initialLoadingData = MutableLiveData<LoadingState>()
    val initialError = MutableLiveData<ErrorData>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, QuoteModel>) {
        scope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                useCase.invoke(userName, 1)
            }
            initialLoadingData.value = LoadingState.IS_LOADING
            when (result) {
                is Outcome.Success -> {
                    initialLoadingData.value = LoadingState.IS_LOADED
                    isLastPage = result.data.isLastPage
                    insertItem(insertQuoteUseCase, result.data.quoteList)
                    callback.onResult(result.data.quoteList, null, 2)
                }
                is Outcome.Error -> {
                    initialError.value = ErrorData(
                        ErrorDataType.RECOVERABLE,
                        StringPresenter(R.string.error_fetch_data_title),
                        StringPresenter(R.string.error_fetch_data_description),
                        StringPresenter(R.string.retry)
                    )
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, QuoteModel>) {
        if(!isLastPage) {
            scope.launch(Dispatchers.Main) {
                val result = withContext(Dispatchers.IO) {
                    useCase.invoke(userName, params.key)
                }
                when (result) {
                    is Outcome.Success -> {
                        isLastPage = result.data.isLastPage
                        insertItem(insertQuoteUseCase, result.data.quoteList)
                        callback.onResult(result.data.quoteList, params.key + 1)
                    }
                    is Outcome.Error -> {
                    }
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, QuoteModel>) {
    }

    private suspend fun insertItem(insertQuoteUseCase: InsertQuoteUseCase, list:List<QuoteModel>) {
        for(item in list) {
            insertQuoteUseCase.invoke(item)
        }
    }

}