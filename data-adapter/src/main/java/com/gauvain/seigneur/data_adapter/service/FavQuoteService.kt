package com.gauvain.seigneur.data_adapter.service

import com.gauvain.seigneur.data_adapter.model.Quotes
import com.gauvain.seigneur.data_adapter.model.Session
import com.gauvain.seigneur.data_adapter.model.User
import com.gauvain.seigneur.data_adapter.model.UserSession
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FavQuoteService {
    @POST("session")
    fun postSession(@Body body: UserSession): Call<Session>

    @GET("quotes")
    fun getQuotes(
        @Query("filter") user: String,
        @Query("type") type: String,
        @Query("page") page: Int): Call<Quotes>

}