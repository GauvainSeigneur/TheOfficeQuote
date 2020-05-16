package com.gauvain.seigneur.data_adapter

import com.gauvain.seigneur.data_adapter.adapter.CreateSessionAdapter
import com.gauvain.seigneur.data_adapter.mock.AdapterOutcomeModelMock
import com.gauvain.seigneur.data_adapter.mock.FavQuoteServiceMock
import com.gauvain.seigneur.data_adapter.mock.ResponseGsonObjectMock
import com.gauvain.seigneur.data_adapter.model.Session
import com.gauvain.seigneur.data_adapter.service.FavQuoteService
import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.provider.CreateSessionException
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.Before
import org.mockito.MockitoAnnotations

class CreateSessionAdapterTest {

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `When we receive object from provider it must return UserSession`() {
        val serviceSuccessResponse: FavQuoteService =
            FavQuoteServiceMock.createServiceWithResponses(ResponseGsonObjectMock.createSuccessSessionResponse())
        val provider = CreateSessionAdapter(serviceSuccessResponse)
        val result = runCatching {
            provider.createSession("login", "password")
        }
        Truth.assertThat(result.isSuccess).isNotNull()
        Truth.assertThat(result.getOrNull()).isEqualTo(AdapterOutcomeModelMock.createUserSessionModelSuccessOutCome())
    }

    @Test
    fun `When we receive only a message from provider it must return Exception`() {
        val messageResponse: FavQuoteService =
            FavQuoteServiceMock.createServiceWithResponses(ResponseGsonObjectMock.createMessageSessionResponse())
        val provider = CreateSessionAdapter(messageResponse)
        val result = runCatching {
            provider.createSession("login", "password")
        }
        Truth.assertThat(result.exceptionOrNull()).isNotNull()
        Truth.assertThat(result.exceptionOrNull()).isInstanceOf(CreateSessionException::class.java)
        Truth.assertThat((result.exceptionOrNull() as CreateSessionException).type).isEqualTo(
            RequestExceptionType.UNAUTHORIZED
        )
        Truth.assertThat((result.exceptionOrNull() as CreateSessionException).description).isEqualTo(
            "User login or password is missing.")
    }

}
