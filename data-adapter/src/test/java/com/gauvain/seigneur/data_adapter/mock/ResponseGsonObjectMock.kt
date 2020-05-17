package com.gauvain.seigneur.data_adapter.mock

import com.gauvain.seigneur.data_adapter.model.BaseResponse
import com.gauvain.seigneur.data_adapter.model.Quote
import com.gauvain.seigneur.data_adapter.model.Quotes
import com.gauvain.seigneur.data_adapter.model.Session
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Response

object ResponseGsonObjectMock {

    fun createSuccessSessionResponse(): Session {
        val type = object : TypeToken<Session>() {
        }.type
        return GsonBuilder().create().fromJson(
            """
                {
                     "User-Token": "abc123",
                     "login": "user_login",
                     "email": "user_email"
                }
                    """.trimIndent(), type
        )
    }

    fun createSuccessQuotesResponse(): Quotes {
        val type = object : TypeToken<Quotes>() {
        }.type
        return GsonBuilder().create().fromJson(
            """
                {
                    "page": 1,
                    "last_page": false,
                    "quotes": [
                        {
                            "id": 33723,
                            "dialogue": false,
                            "private": false,
                            "tags": [
                                "humor"
                            ],
                            "url": "https://favqs.com/quotes/thomas-carlyle/33723-humor-has-jus-",
                            "favorites_count": 2,
                            "upvotes_count": 1,
                            "downvotes_count": 0,
                            "author": "Thomas Carlyle",
                            "author_permalink": "thomas-carlyle",
                            "body": "Humor has justly been regarded as the finest perfection of poetic genius."
                        }
                    ]
                }
                    """.trimIndent(), type
        )
    }



    fun createMessageSessionResponse(): Session {
        val type = object : TypeToken<Session>() {
        }.type
        return GsonBuilder().create().fromJson(
            """
                {
                     "User-Token": "abc123",
                     "login": "user_login",
                     "email": "user_email"
                }
                    """.trimIndent(), type
        )
    }

    fun createMessageQuoteResponse(): Quotes {
        val type = object : TypeToken<Quotes>() {
        }.type
        return GsonBuilder().create().fromJson(
            """
                {
                "error_code": 23,
                "message": "Oops"
                }
                    """.trimIndent(), type
        )
    }

}