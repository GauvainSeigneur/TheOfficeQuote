package com.gauvain.seigneur.domain

import com.gauvain.seigneur.domain.mock.OutComeModelMock
import com.gauvain.seigneur.domain.mock.ProviderModelMock
import com.gauvain.seigneur.domain.model.ErrorType
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.model.RequestExceptionType
import com.gauvain.seigneur.domain.provider.GetQuotesException
import com.gauvain.seigneur.domain.provider.GetQuotesProvider
import com.gauvain.seigneur.domain.usecase.GetUserFavoriteQuotesUseCaseImpl
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.*

class GetFavoriteQuotesUseCaseImplTest {

    @Mock
    private lateinit var provider: GetQuotesProvider
    @InjectMocks
    private lateinit var useCase: GetUserFavoriteQuotesUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `When we receive success from provider adapter must return OutCome Success`() {
        runBlockingTest {
            given(provider.get ("filter", "user", 1)).willReturn(ProviderModelMock
                .createUserFavQuoteModel())
            val outcome = useCase.invoke("filter", 1)
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(OutComeModelMock.createUserFavQuotesModelOutCome())
        }
    }

    @Test
    fun `When we receive ERROR UNKNOWN from provider adapter must return OutCome Error`() {
        runBlockingTest {
            given(provider.get ("filter", "user", 1)).willThrow(
                GetQuotesException(RequestExceptionType.ERROR_UNKNOWN)
            )
            val outcome = useCase.invoke("filter", 1)
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Error(ErrorType.ERROR_UNKNOWN))
        }
    }

    @Test
    fun `When we receive ERROR BODY NULL from provider adapter must return OutCome Error`() {
        runBlockingTest {
            given(provider.get ("filter", "user", 1)).willThrow(
                GetQuotesException(RequestExceptionType.BODY_NULL)
            )
            val outcome = useCase.invoke("filter", 1)
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Error(ErrorType.ERROR_UNKNOWN))
        }
    }

    @Test
    fun `When we receive UNAUTHORIZED from provider adapter must return OutCome Error`() {
        runBlockingTest {
            given(provider.get ("filter", "user", 1)).willThrow(
                GetQuotesException(RequestExceptionType.UNAUTHORIZED)
            )
            val outcome = useCase.invoke("filter", 1)
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Error(ErrorType.ERROR_UNAUTHORIZED))
        }
    }

    @Test
    fun `When we receive CONNECTION_LOST from provider adapter must return OutCome Error`() {
        runBlockingTest {
            given(provider.get ("filter", "user", 1)).willThrow(
                GetQuotesException(RequestExceptionType.CONNECTION_LOST)
            )
            val outcome = useCase.invoke("filter", 1)
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Error(ErrorType.ERROR_CONNECTION_LOST))
        }
    }

    @Test
    fun `When we receive UNKNOWN_HOST from provider adapter must return OutCome Error`() {
        runBlockingTest {
            given(provider.get ("filter", "user", 1)).willThrow(
                GetQuotesException(RequestExceptionType.UNKNOWN_HOST)
            )
            val outcome = useCase.invoke("filter", 1)
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Error(ErrorType.ERROR_UNKNOWN_HOST))
        }
    }

    @Test
    fun `When we receive SERVER_INTERNAL_ERROR from provider adapter must return OutCome Error`() {
        runBlockingTest {
            given(provider.get ("filter", "user", 1)).willThrow(
                GetQuotesException(RequestExceptionType.SERVER_INTERNAL_ERROR)
            )
            val outcome = useCase.invoke("filter", 1)
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Error(ErrorType.ERROR_INTERNAL_SERVER))
        }
    }

    @Test
    fun `When we receive UNKNOWN_OBJECT from provider adapter must return OutCome Error`() {
        runBlockingTest {
            given(provider.get ("filter", "user", 1)).willThrow(
                GetQuotesException(RequestExceptionType.UNKNOWN_OBJECT)
            )
            val outcome = useCase.invoke("filter", 1)
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Error(ErrorType.ERROR_UNKNOWN))
        }
    }
}