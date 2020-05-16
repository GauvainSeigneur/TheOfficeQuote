package com.gauvain.seigneur.data_adapter.mock

import com.gauvain.seigneur.domain.model.UserSessionModel

object AdapterOutcomeModelMock {

    fun createUserSessionModelSuccessOutCome(): UserSessionModel =
        UserSessionModel(userToken = "abc123", login = "user_login", email = "user_email")
}