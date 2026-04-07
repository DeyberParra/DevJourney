package com.app.countries.data.service

import com.app.countries.data.model.response.CountryDetailResponseDto
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {
    @GET("name/{countryName}")
    suspend fun getDetailCountry(
        @Path("countryName") countryName: String
    ) : Response<List<CountryDetailResponseDto>>
}