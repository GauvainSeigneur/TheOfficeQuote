package com.gauvain.seigneur.theofficequote.view.favQuotes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCase
import com.gauvain.seigneur.domain.usecase.InsertQuoteUseCase
import kotlinx.coroutines.CoroutineScope

class QuoteDataSourceFactory(
    val userName:String,
    val scope: CoroutineScope,
    val useCase: GetUserFavoriteQuotesUseCase,
    val insertQuoteUseCase: InsertQuoteUseCase
) : DataSource.Factory<Int, QuoteModel>() {

    val quoteDataSourceLiveData = MutableLiveData<QuoteDataSource>()

    override fun create(): DataSource<Int, QuoteModel> {
        Log.d("QuoteDataSourceFactory", "called")
        val newsDataSource = QuoteDataSource(userName, scope, useCase, insertQuoteUseCase)
        quoteDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}