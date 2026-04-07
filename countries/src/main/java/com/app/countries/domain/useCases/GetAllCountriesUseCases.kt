package com.app.countries.domain.useCases

import com.app.base.data.network.BaseResource
import com.app.base.data.network.BaseResourceError
import com.app.countries.data.repository.CountryRepository
import com.app.countries.domain.model.CountryModel
import javax.inject.Inject

/**
 * Caso de uso para obtener la lista de todos los países desde una fuente local.
 */
class GetAllCountriesUseCases @Inject constructor(
    private val repository: CountryRepository
) {
    /**
     * Ejecuta la llamada al repositorio para obtener los países locales y 
     * envuelve el resultado en un [BaseResource.Success].
     */
    suspend operator fun invoke(): BaseResource<List<CountryModel>> {
        return try {
            val countries = repository.getLocalCountries()
            BaseResource.Success(countries)
        } catch (e: Exception) {
            BaseResource.Error(BaseResourceError.UnknownError(e))
        }
    }
}
