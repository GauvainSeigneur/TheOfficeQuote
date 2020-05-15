package com.gauvain.seigneur.data_adapter.injection

import androidx.room.Room
import com.gauvain.seigneur.data_adapter.database.TheOfficequoteDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataSourceModule = module {
    single {
        Room.databaseBuilder(androidApplication(), TheOfficequoteDataBase::class.java,
            "the-office-quote-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<TheOfficequoteDataBase>().tokenDao() }
}