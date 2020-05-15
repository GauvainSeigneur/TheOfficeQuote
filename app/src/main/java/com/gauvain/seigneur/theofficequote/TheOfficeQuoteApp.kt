package com.gauvain.seigneur.theofficequote

import android.app.Application
import com.gauvain.seigneur.data_adapter.injection.localDataSourceModule
import com.gauvain.seigneur.data_adapter.injection.remoteDataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TheOfficeQuoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TheOfficeQuoteApp)
            androidFileProperties()
            modules(
                listOf(
                    remoteDataSourceModule,
                    localDataSourceModule
                )
            )
        }
    }
}