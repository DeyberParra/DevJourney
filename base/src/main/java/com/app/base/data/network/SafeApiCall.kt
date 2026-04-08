package com.app.base.data.network

import retrofit2.Response
import java.io.IOException

/**
 * @author : DeyberParra
 * @description :Generic function to safely perform network calls.
 * @param call The Retrofit call to execute.
 * @param map A function to transform the response DTO into a domain object (Model).
 * @return A [BaseResource] representing the result (Success, Error, or Loading).
 */
suspend fun <T, R> safeApiCall(
    call: suspend () -> Response<T>,
    map: (T) -> R
): BaseResource<R> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                BaseResource.Success(map(body))
            } else {
                BaseResource.Error(BaseResourceError.ServerError)
            }
        } else {
            val error = when (response.code()) {
                401 -> BaseResourceError.Unauthorized
                500 -> BaseResourceError.ServerError
                else -> BaseResourceError.ServerError
            }
            BaseResource.Error(error)
        }
    } catch (e: IOException) {
        BaseResource.Error(BaseResourceError.NetworkError)
    } catch (e: Exception) {
        BaseResource.Error(BaseResourceError.UnknownError(e))
    }
}
