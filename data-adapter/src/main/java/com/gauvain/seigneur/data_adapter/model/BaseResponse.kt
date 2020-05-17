package com.gauvain.seigneur.data_adapter.model

abstract class BaseResponse {
    abstract val  errorCode: Int?
    abstract val message: String?
}
