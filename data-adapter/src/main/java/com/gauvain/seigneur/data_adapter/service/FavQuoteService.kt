package com.gauvain.seigneur.data_adapter.service

import com.gauvain.seigneur.data_adapter.model.Session
import com.gauvain.seigneur.data_adapter.model.User
import com.gauvain.seigneur.data_adapter.model.UserSession
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FavQuoteService {
    @POST("session")
    fun postSession(@Body body: UserSession): Call<Session>

}