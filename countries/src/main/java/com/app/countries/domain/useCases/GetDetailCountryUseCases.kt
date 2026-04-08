package com.app.countries.domain.useCases

import com.app.base.data.network.BaseResource
import com.app.base.data.network.safeApiCall
import com.app.countries.data.model.response.CountryDetailModel
import com.app.countries.data.repository.CountryRepository
import com.app.countries.domain.mapper.toModel
import javax.inject.Inject

/**
 * get All contries
 */
class GetDetailCountryUseCases @Inject constructor(
    private val repository: CountryRepository
) {
    /**
     * Call to repository and mapper base response to viewModel
     */
    suspend operator fun invoke(country : String): BaseResource<List<CountryDetailModel>> {
        return safeApiCall(
            call = { repository.getDetailCountry(country) },
            map = {
                dtos -> dtos.map { it.toModel() }
            }
        )
    }
}
