package com.gauvain.seigneur.data_adapter.service

import com.gauvain.seigneur.data_adapter.model.*
import retrofit2.Call
import retrofit2.http.*

interface FavQuoteService {
    @POST("session")
    fun postSession(@Body body: UserSession): Call<Session>

    @GET("quotes")
    fun getQuotes(
        @Query("filter") user: String,
        @Query("type") type: String,
        @Query("page") page: Int): Call<Quotes>

    @GET("users/{login}")
    fun getUser(@Path(value = "login", encoded = true) login: String): Call<CurrentUser>

    @GET("quotes")
    fun getRandom(): Call<SingleQuote>

}