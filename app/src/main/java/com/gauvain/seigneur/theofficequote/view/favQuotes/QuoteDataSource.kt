package com.gauvain.seigneur.theofficequote.view.favQuotes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCase
import com.gauvain.seigneur.theofficequote.model.QuoteItemData
import com.gauvain.seigneur.theofficequote.model.toData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuoteDataSource(
    val userName:String,
    val scope: CoroutineScope,
    val useCase: GetUserFavoriteQuotesUseCase) : PageKeyedDataSource<Int, QuoteItemData>(){


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, QuoteItemData>) {
        scope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                useCase.invoke(userName, 1)
            }
            when (result) {
                is Outcome.Success -> {
                    Log.d("pagingQuotes initial", "Success ${result.data.toData().quotes}")
                    callback.onResult(result.data.toData().quotes, null, 2)
                }
                is Outcome.Error -> {
                    Log.d("pagingQuotes initial", "error")
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, QuoteItemData>) {
        scope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                useCase.invoke(userName, params.key)
            }
            when (result) {
                is Outcome.Success -> {

                    Log.d("pagingQuotes", "Success")
                    callback.onResult(result.data.toData().quotes, params.key + 1)
                }
                is Outcome.Error -> {

                    Log.d("pagingQuotes", "Error")
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, QuoteItemData>) {
    }

    private fun updateState() {

    }

    fun retry() {

    }

}