package com.gauvain.seigneur.data_adapter.injection

import android.util.Log
import com.gauvain.seigneur.data_adapter.adapter.GetTokenAdapter
import com.gauvain.seigneur.data_adapter.service.FavQuoteService
import com.gauvain.seigneur.data_adapter.service.HeaderApiTokenInterceptor
import com.gauvain.seigneur.data_adapter.service.HeaderUserTokenInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    factory<Interceptor> {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("FAVQUOTE_SERVICE", it)
        }
        ).setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    //set as factory in order to update GetTokenAdapter.constToken value when it is done
    factory {
        OkHttpClient.Builder()
            .addInterceptor(get())
            .addNetworkInterceptor(
                HeaderApiTokenInterceptor(
                    getProperty("api_key")
                )
            )
            .addNetworkInterceptor(
                HeaderUserTokenInterceptor(GetTokenAdapter.constToken)
            )
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(getProperty("server_url") as String)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(FavQuoteService::class.java) }
}