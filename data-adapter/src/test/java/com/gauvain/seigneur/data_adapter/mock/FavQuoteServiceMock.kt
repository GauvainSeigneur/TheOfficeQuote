package com.gauvain.seigneur.data_adapter.mock

import com.gauvain.seigneur.data_adapter.model.*
import com.gauvain.seigneur.data_adapter.service.FavQuoteService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.Calls
import retrofit2.mock.MockRetrofit

object FavQuoteServiceMock {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://favqs.com/api/")
        .build()
    val behaviorDelegate: BehaviorDelegate<FavQuoteService> =
        MockRetrofit.Builder(retrofit).build().create(FavQuoteService::class.java)

    fun createServiceThatFail(t: Throwable) =
        object : FavQuoteService {
            override fun postSession(body: UserSession): Call<Session> {
                return behaviorDelegate.returning(Calls.failure<Throwable>(t))
                    .postSession(UserSession(User("login", "password")))
            }

            override fun getQuotes(user: String, type: String, page: Int): Call<Quotes> {
                return behaviorDelegate.returning(Calls.failure<Throwable>(t))
                    .getQuotes("filter", "type", 0)
            }

            override fun getUser(login: String): Call<CurrentUser> {
                return behaviorDelegate.returning(Calls.failure<Throwable>(t))
                    .getUser("login")
            }

        }

    fun createServiceWithResponses(response: Any? = null) =
        object : FavQuoteService {
            override fun postSession(body: UserSession): Call<Session> {
                return behaviorDelegate.returningResponse(response).postSession(UserSession(User("login", "password")))
            }

            override fun getQuotes(user: String, type: String, page: Int): Call<Quotes> {
                return behaviorDelegate.returningResponse(response).getQuotes("user", "type", 0)
            }

            override fun getUser(login: String): Call<CurrentUser> {
                return behaviorDelegate.returningResponse(response).getUser("login")
            }
        }
}