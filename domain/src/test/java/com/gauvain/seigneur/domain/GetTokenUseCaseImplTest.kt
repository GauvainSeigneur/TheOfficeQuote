package com.gauvain.seigneur.domain

import com.gauvain.seigneur.domain.model.ErrorType
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.provider.GetTokenException
import com.gauvain.seigneur.domain.provider.GetTokenProvider
import com.gauvain.seigneur.domain.provider.InsertTokenException
import com.gauvain.seigneur.domain.usecase.GetTokenUseCaseImpl
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.*

class GetTokenUseCaseImplTest {

    @Mock
    private lateinit var provider: GetTokenProvider
    @InjectMocks
    private lateinit var useCase: GetTokenUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `When we receive success from provider adapter must return OutCome Success`() {
        runBlockingTest {
            given(provider.getToken()).willReturn("token")
            val outcome = useCase.invoke()
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Success("token"))
        }
    }

    @Test
    fun `When we receive ERROR from provider adapter must return OutCome Error`() {
        runBlockingTest {
            given(provider.getToken ()).willThrow(GetTokenException("Error"))
            val outcome = useCase.invoke()
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Error(ErrorType.ERROR_UNKNOWN))
        }
    }

}