package com.gauvain.seigneur.theofficequote.view.favQuotes

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCase
import com.gauvain.seigneur.domain.usecase.InsertQuoteUseCase
import com.gauvain.seigneur.theofficequote.model.QuoteItemData
import kotlinx.coroutines.CoroutineScope

class QuoteDataSourceFactory(
    val userName:String,
    val scope: CoroutineScope,
    val useCase: GetUserFavoriteQuotesUseCase,
    val insertQuoteUseCase: InsertQuoteUseCase
)
    : DataSource.Factory<Int, QuoteItemData>() {

    val quoteDataSourceLiveData = MutableLiveData<QuoteDataSource>()

    override fun create(): DataSource<Int, QuoteItemData> {
        val newsDataSource = QuoteDataSource(userName, scope, useCase, insertQuoteUseCase)
        quoteDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}