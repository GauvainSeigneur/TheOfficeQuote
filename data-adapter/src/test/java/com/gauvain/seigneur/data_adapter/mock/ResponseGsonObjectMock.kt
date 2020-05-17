package com.gauvain.seigneur.data_adapter.mock

import com.gauvain.seigneur.data_adapter.model.*
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Response

object ResponseGsonObjectMock {

    fun createSuccessCurrentUserResponse(): CurrentUser {
        val type = object : TypeToken<CurrentUser>() {
        }.type
        return GsonBuilder().create().fromJson(
            """
                {
                    "login": "gose",
                    "pic_url": "https://pbs.twimg.com/profile_images/2160924471/Screen_Shot_2012-04-23_at_9.23.44_PM_.png",
                    "public_favorites_count": 520,
                    "followers": 12,
                    "following": 23,
                    "pro": true,
                    "account_details": {
                        "email": "gose@favqs.com",
                        "private_favorites_count": 22,
                        "active_theme_id": 1,
                        "pro_expiration": "2015-03-13T07:19:06.133-05:00"
                    }
                }
                """.trimIndent(), type
        )
    }

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
                    "error_code": 23,
                    "message": "User login or password is missing"
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

    fun createMessageGetUserResponse(): CurrentUser {
        val type = object : TypeToken<CurrentUser>() {
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