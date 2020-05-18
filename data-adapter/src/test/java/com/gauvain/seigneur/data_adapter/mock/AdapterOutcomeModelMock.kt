package com.gauvain.seigneur.data_adapter.mock

import com.gauvain.seigneur.domain.model.*
import com.gauvain.seigneur.domain.utils.SERVER_DATE_FORMAT
import com.gauvain.seigneur.domain.utils.toDate

object AdapterOutcomeModelMock {

     fun createCurrentUserSuccessOutCome(): CurrentUserModel =
        CurrentUserModel(
            login = "gose",
            picUrl = "https://pbs.twimg.com/profile_images/2160924471/Screen_Shot_2012-04-23_at_9.23.44_PM_.png",
            publicFavCount = 520,
            followers = 12,
            following = 23,
            isPro = true,
            accountDetails = AccountDetailsModel(
                email = "gose@favqs.com",
                privateFavCount = 22,
                activeThemeId = 1,
                proExpiration = "2015-03-13T07:19:06.133-05:00".toDate(SERVER_DATE_FORMAT)
            )
        )



    fun createUserSessionModelSuccessOutCome(): UserSessionModel =
        UserSessionModel(userToken = "abc123", login = "user_login", email = "user_email")

    fun createUserFavQuoteModelSuccessOutCome(): QuotesModel =
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