package com.app.countries.data.repository

import com.app.countries.domain.model.CountryModel
import com.app.countries.data.model.response.CountryDetailResponseDto
import retrofit2.Response

interface CountryRepository {
    suspend fun getLocalCountries(): List<CountryModel>
    suspend fun getDetailCountry(countryName : String) : Response<List<CountryDetailResponseDto>>
}