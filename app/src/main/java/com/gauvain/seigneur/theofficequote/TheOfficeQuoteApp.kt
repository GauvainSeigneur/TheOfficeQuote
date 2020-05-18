package com.gauvain.seigneur.theofficequote

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import com.gauvain.seigneur.data_adapter.injection.adapterModule
import com.gauvain.seigneur.data_adapter.injection.localDataSourceModule
import com.gauvain.seigneur.data_adapter.injection.remoteDataSourceModule
import com.gauvain.seigneur.theofficequote.injection.useCaseModule
import com.gauvain.seigneur.theofficequote.injection.viewModelModule
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TheOfficeQuoteApp : Application(), ImageLoaderFactory{

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TheOfficeQuoteApp)
            androidFileProperties()
            modules(
                listOf(
                    remoteDataSourceModule,
                    localDataSourceModule,
                    adapterModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this@TheOfficeQuoteApp).apply {
            componentRegistry {
                add(SvgDecoder(this@TheOfficeQuoteApp))
            }
        }
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this@TheOfficeQuoteApp))
                    .build()
            }
            .build()
    }
}