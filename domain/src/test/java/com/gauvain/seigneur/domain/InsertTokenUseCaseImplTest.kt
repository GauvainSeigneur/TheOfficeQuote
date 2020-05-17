package com.gauvain.seigneur.domain

import com.gauvain.seigneur.domain.model.ErrorType
import com.gauvain.seigneur.domain.model.Outcome
import com.gauvain.seigneur.domain.provider.InsertTokenException
import com.gauvain.seigneur.domain.provider.InsertTokenProvider
import com.gauvain.seigneur.domain.usecase.InsertTokenUseCaseImpl
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.*

class InsertTokenUseCaseImplTest {

    @Mock
    private lateinit var provider: InsertTokenProvider
    @InjectMocks
    private lateinit var useCase: InsertTokenUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `When we receive success from provider adapter must return OutCome Success`() {
        runBlockingTest {
            given(provider.insert ("token", "login")).willReturn(1L)
            val outcome = useCase.invoke("token", "login")
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Success(1L))
        }
    }

    @Test
    fun `When we receive ERROR from provider adapter must return OutCome Error`() {
        runBlockingTest {
            given(provider.insert ("token", "login")).willThrow(InsertTokenException("Error"))
            val outcome = useCase.invoke("token", "login")
            assertThat(outcome).isNotNull()
            assertThat(outcome).isEqualTo(Outcome.Error(ErrorType.ERROR_UNKNOWN))
        }
    }

}