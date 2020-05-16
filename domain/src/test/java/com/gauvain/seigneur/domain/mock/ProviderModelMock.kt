package com.gauvain.seigneur.domain.mock

import com.gauvain.seigneur.domain.model.QuoteModel
import com.gauvain.seigneur.domain.model.QuotesModel
import com.gauvain.seigneur.domain.model.UserSessionModel

object ProviderModelMock {

    fun createUserSessionModel(): UserSessionModel = UserSessionModel(
        "userToken",
        "userEmail",
        "userLogin"
    )

    fun createUserFavQuoteModel(): QuotesModel =
        QuotesModel(
            page = 1, isLastPage = false, quoteList = listOf(
                QuoteModel(
                    id = 33723,
                    isDialog = false,
                    isPrivate = false,
                    tags = listOf("humor"),
                    url = "https://favqs.com/quotes/thomas-carlyle/33723-humor-has-jus-",
                    favoritesCount = 2,
                    upvotesCount = 1,
                    downvotesCount = 0,
                    author = "Thomas Carlyle",
                    authorPermalink = "thomas-carlyle",
                    body = "Humor has justly been regarded as the finest perfection of poetic genius."
                )
            )
        )
}