package com.app.base.data.network

import retrofit2.Response
import java.io.IOException

/**
 * Función genérica para realizar llamadas a red de forma segura.
 * 
 * @param call La llamada de Retrofit a realizar.
 * @param map Una función para transformar el DTO de respuesta en el objeto de dominio (Model).
 * @return Un [BaseResource] representando el resultado (Success, Error o Loading).
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
                // El cuerpo es nulo, lo cual suele ser un error del servidor o respuesta inesperada
                BaseResource.Error(BaseResourceError.ServerError)
            }
        } else {
            // El servidor respondió con un código de error (4xx o 5xx)
            val error = when (response.code()) {
                401 -> BaseResourceError.Unauthorized
                500 -> BaseResourceError.ServerError
                else -> BaseResourceError.ServerError
            }
            BaseResource.Error(error)
        }
    } catch (e: IOException) {
        // Error de red (sin internet, timeout, etc.)
        BaseResource.Error(BaseResourceError.NetworkError)
    } catch (e: Exception) {
        // Cualquier otro error inesperado (mapeo, etc.)
        BaseResource.Error(BaseResourceError.UnknownError(e))
    }
}
