package com.gauvain.seigneur.data_adapter.adapter

import com.gauvain.seigneur.data_adapter.database.TheOfficequoteDataBase
import com.gauvain.seigneur.data_adapter.model.*
import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.provider.*

class InsertQuoteItemAdapter(val dataBase: TheOfficequoteDataBase) :
    InsertQuoteItemProvider {

    override suspend fun insert(quoteModel: QuoteModel): Long {
        var transaction: Long? = null
        runCatching {
            dataBase.quoteDao().insertQuote(quoteModel.toEntity())
        }
            .onFailure {
                throw InsertTokenException(it.message)
            }
            .onSuccess {
                transaction = it
            }

        transaction?.let {
            return it
        } ?: throw InsertTokenException("Unknown Error")
    }
}

fun QuoteModel.toEntity(): QuoteItemEntity = QuoteItemEntity(
    id = this.id,
    isDialog = this.isDialog,
    isPrivate = this.isPrivate,
    tags = this.tags,
    url = this.url,
    favoritesCount = this.favoritesCount,
    upvotesCount = this.upvotesCount,
    downvotesCount = this.downvotesCount,
    author = this.author,
    authorPermalink = this.authorPermalink,
    body = this.body
)

