package com.gauvain.seigneur.theofficequote.view.favQuotes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.gauvain.seigneur.data_adapter.model.Quote
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCase
import com.gauvain.seigneur.domain.usecase.InsertQuoteUseCase
import com.gauvain.seigneur.theofficequote.model.QuoteItemData
import com.gauvain.seigneur.theofficequote.model.toData
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

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, QuoteModel>) {
        scope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                useCase.invoke(userName, 1)
            }
            when (result) {
                is Outcome.Success -> {
                    Log.d("pagingQuotes initial", "Success ${result.data.toData().quotes}")
                    isLastPage = result.data.isLastPage
                    insertItem(insertQuoteUseCase, result.data.quoteList)
                    callback.onResult(result.data.quoteList, null, 2)
                }
                is Outcome.Error -> {
                    Log.d("pagingQuotes initial", "error")
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, QuoteModel>) {
        if(!isLastPage) {
            Log.d("pagingQuotes after", "called")
            scope.launch(Dispatchers.Main) {
                val result = withContext(Dispatchers.IO) {
                    useCase.invoke(userName, params.key)
                }
                when (result) {
                    is Outcome.Success -> {
                        Log.d("pagingQuotes after", "Success")
                        isLastPage = result.data.isLastPage
                        insertItem(insertQuoteUseCase, result.data.quoteList)
                        callback.onResult(result.data.quoteList, params.key + 1)
                    }
                    is Outcome.Error -> {

                        Log.d("pagingQuotes after", "Error")
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

    fun retry() {

    }

}