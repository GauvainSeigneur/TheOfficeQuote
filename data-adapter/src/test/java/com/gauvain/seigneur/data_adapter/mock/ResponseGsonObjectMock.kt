package com.gauvain.seigneur.data_adapter.mock

import com.gauvain.seigneur.data_adapter.model.BaseResponse
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

    fun createMessageSessionResponse(): Session {
        val type = object : TypeToken<Session>() {
        }.type
        return GsonBuilder().create().fromJson(
            """
                {
                "error_code": 23,
                "message": "User login or password is missing."
                }
                    """.trimIndent(), type
        )
    }

}