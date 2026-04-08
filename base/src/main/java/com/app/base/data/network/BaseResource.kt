package com.app.base.data.network
/**
 * Types of Results */
sealed class BaseResource<out T> {
    data class Success<out T>(val data: T) : BaseResource<T>()
    data class Error(val error : BaseResourceError) : BaseResource<Nothing>()
}

sealed class BaseResourceError{
    abstract val message: String
    object NetworkError : BaseResourceError() {
        override val message: String
            get() = "Network Error, try layer"
    }

    object ServerError : BaseResourceError() {
        override val message: String
            get() = "Server No Response, try later"
    }

    object Unauthorized : BaseResourceError() {
        override val message: String
            get() = "Call to Support for more information"
    }

    data class UnknownError(val throwable: Throwable) : BaseResourceError(){
        override val message = throwable.localizedMessage ?: "Sorry , call to support"
    }
}