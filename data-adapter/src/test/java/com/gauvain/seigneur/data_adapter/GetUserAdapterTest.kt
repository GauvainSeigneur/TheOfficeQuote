package com.gauvain.seigneur.data_adapter

import com.gauvain.seigneur.data_adapter.adapter.GetUserAdapter
import com.gauvain.seigneur.data_adapter.mock.AdapterOutcomeModelMock
import com.gauvain.seigneur.data_adapter.mock.FavQuoteServiceMock
import com.gauvain.seigneur.data_adapter.mock.ResponseGsonObjectMock
import com.gauvain.seigneur.data_adapter.service.FavQuoteService
import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.provider.GetUserException
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.Before
import org.mockito.MockitoAnnotations
import java.net.UnknownHostException

class GetUserAdapterTest {

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `When we receive object from provider it must return UserSession`() {
        val serviceSuccessResponse: FavQuoteService =
            FavQuoteServiceMock.createServiceWithResponses(ResponseGsonObjectMock.createSuccessCurrentUserResponse())
        val provider = GetUserAdapter(serviceSuccessResponse)
        val result = runCatching {
            provider.get("login")
        }
        Truth.assertThat(result.isSuccess).isNotNull()
        Truth.assertThat(result.getOrNull()).isEqualTo(AdapterOutcomeModelMock.createCurrentUserSuccessOutCome())
    }

    @Test
    fun `When we receive only a message from provider it must return Exception`() {
        val messageResponse: FavQuoteService =
            FavQuoteServiceMock.createServiceWithResponses(ResponseGsonObjectMock.createMessageGetUserResponse())
        val provider = GetUserAdapter(messageResponse)
        val result = runCatching {
            provider.get("login")
        }
        Truth.assertThat(result.exceptionOrNull()).isNotNull()
        Truth.assertThat(result.exceptionOrNull()).isInstanceOf(GetUserException::class.java)
        Truth.assertThat((result.exceptionOrNull() as GetUserException).type).isEqualTo(
            RequestExceptionType.UNAUTHORIZED
        )
        Truth.assertThat((result.exceptionOrNull() as GetUserException).description).isEqualTo(
            "Oops")
    }

    @Test
    fun `When we receive UNKNOWN HOST ERROR from provider it must return GetQuotesException`() {
        val serviceFailedResponse: FavQuoteService =
            FavQuoteServiceMock.createServiceThatFail(UnknownHostException())
        val provider = GetUserAdapter(serviceFailedResponse)
        val result = runCatching {
            provider.get("login")
        }
        Truth.assertThat(result.exceptionOrNull()).isNotNull()
        Truth.assertThat(result.exceptionOrNull()).isInstanceOf(GetUserException::class.java)
        Truth.assertThat((result.exceptionOrNull() as GetUserException).type).isEqualTo(
            RequestExceptionType.UNKNOWN_HOST
        )
    }


}
