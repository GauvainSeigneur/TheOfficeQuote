package com.gauvain.seigneur.data_adapter

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.gauvain.seigneur.data_adapter.adapter.InsertTokenAdapter
import com.gauvain.seigneur.data_adapter.database.TheOfficequoteDataBase
import com.gauvain.seigneur.data_adapter.database.TokenDao
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class InsertTokenAdapterTest {
    private lateinit var dataBase: TheOfficequoteDataBase
    private lateinit var dao: TokenDao
    private lateinit var adapter: InsertTokenAdapter

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataBase = Room.inMemoryDatabaseBuilder(context, TheOfficequoteDataBase::class.java).build()
        dao = dataBase.tokenDao()
        adapter = InsertTokenAdapter(dataBase)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        dataBase.close()
    }

    @Test
    fun writeTokenInDataBaseAndGetItTransactionId() {
        runBlockingTest {
            val result = adapter.insert("tokenValue", "login")
            assert(result==1L)
        }
    }

}
