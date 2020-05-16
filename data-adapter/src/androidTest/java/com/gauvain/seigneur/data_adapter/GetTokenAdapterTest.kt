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
import com.gauvain.seigneur.data_adapter.adapter.GetTokenAdapter
import com.gauvain.seigneur.data_adapter.model.TokenEntity
import junit.framework.Assert.fail
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.CompletableFuture

@RunWith(AndroidJUnit4::class)
class GetTokenAdapterTest {

    private lateinit var dataBase: TheOfficequoteDataBase
    private lateinit var dao: TokenDao
    private lateinit var adapter: GetTokenAdapter
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataBase = Room.inMemoryDatabaseBuilder(context, TheOfficequoteDataBase::class.java).build()
        dao = dataBase.tokenDao()
        adapter = GetTokenAdapter(dataBase)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        dataBase.close()
    }

    @Test
    fun writeTokenInDataBaseAndGetItsValue() {
        runBlockingTest {
            dao.insertToken(TokenEntity(0, "tokenValue"))
            val result = adapter.getToken()
            assert(result == "tokenValue")
        }
    }

}
