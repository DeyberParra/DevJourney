package com.app.base.data.network

sealed class BaseResource<out T> {
    data class Success<out T>(val data: T) : BaseResource<T>()
    data class Error(val error : BaseResourceError) : BaseResource<Nothing>()
    object Loading : BaseResource<Nothing>()
}

sealed class BaseResourceError{
    object NetworkError : BaseResourceError()
    object ServerError : BaseResourceError()
    object Unauthorized : BaseResourceError()
    data class BusinessError(val message: String) : BaseResourceError()
    data class UnknownError(val throwable: Throwable) : BaseResourceError()
}