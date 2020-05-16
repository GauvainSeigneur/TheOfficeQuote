package com.gauvain.seigneur.data_adapter

import com.gauvain.seigneur.data_adapter.adapter.GetQuotesAdapter
import com.gauvain.seigneur.data_adapter.mock.AdapterOutcomeModelMock
import com.gauvain.seigneur.data_adapter.mock.FavQuoteServiceMock
import com.gauvain.seigneur.data_adapter.mock.ResponseGsonObjectMock
import com.gauvain.seigneur.data_adapter.service.FavQuoteService
import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.provider.GetQuotesException
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.Before
import org.mockito.MockitoAnnotations
import java.net.UnknownHostException

class GetQuoteAdapterTest {

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `When we receive object from provider it must return a list of quotes`() {
        val serviceSuccessResponse: FavQuoteService =
            FavQuoteServiceMock.createServiceWithResponses(ResponseGsonObjectMock.createSuccessQuotesResponse())
        val provider = GetQuotesAdapter(serviceSuccessResponse)
        val result = runCatching {
            provider.get("filter", "type", 0)
        }
        Truth.assertThat(result.isSuccess).isNotNull()
        Truth.assertThat(result.getOrNull()).isEqualTo(AdapterOutcomeModelMock.createUserFavQuoteModelSuccessOutCome())
    }

    @Test
    fun `When we receive only a message from provider it must return Exception`() {
        val messageResponse: FavQuoteService =
            FavQuoteServiceMock.createServiceWithResponses(ResponseGsonObjectMock.createMessageQuoteResponse())
        val provider = GetQuotesAdapter(messageResponse)
        val result = runCatching {
            provider.get("filter", "type", 0)
        }
        Truth.assertThat(result.exceptionOrNull()).isNotNull()
        Truth.assertThat(result.exceptionOrNull()).isInstanceOf(GetQuotesException::class.java)
        Truth.assertThat((result.exceptionOrNull() as GetQuotesException).type).isEqualTo(
            RequestExceptionType.UNAUTHORIZED
        )
        Truth.assertThat((result.exceptionOrNull() as GetQuotesException).description).isEqualTo(
            "Oops")
    }

    @Test
    fun `When we receive UNKNOWN HOST ERROR from provider it must return GetQuotesException`() {
        val serviceFailedResponse: FavQuoteService =
            FavQuoteServiceMock.createServiceThatFail(UnknownHostException())
        val provider = GetQuotesAdapter(serviceFailedResponse)
        val result = runCatching {
            provider.get("filter", "type", 0)
        }
        Truth.assertThat(result.exceptionOrNull()).isNotNull()
        Truth.assertThat(result.exceptionOrNull()).isInstanceOf(GetQuotesException::class.java)
        Truth.assertThat((result.exceptionOrNull() as GetQuotesException).type).isEqualTo(
            RequestExceptionType.UNKNOWN_HOST
        )
    }

}
