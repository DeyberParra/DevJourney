package com.app.countries.domain.useCases

import com.app.base.data.network.BaseResource
import com.app.base.data.network.BaseResourceError
import com.app.countries.data.repository.CountryRepository
import com.app.countries.domain.model.CountryModel
import javax.inject.Inject

class GetAllCountriesUseCases @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(): BaseResource<List<CountryModel>> {
        return try {
            val countries = repository.getLocalCountries()
            BaseResource.Success(countries)
        } catch (e: Exception) {
            BaseResource.Error(BaseResourceError.UnknownError(e))
        }
    }
}
