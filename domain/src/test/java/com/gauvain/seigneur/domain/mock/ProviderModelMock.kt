package com.gauvain.seigneur.domain.mock

import com.gauvain.seigneur.domain.model.UserSessionModel

object ProviderModelMock {

    fun createUserSessionModel(): UserSessionModel = UserSessionModel(
        "userToken",
        "userEmail",
        "userLogin"
    )
}