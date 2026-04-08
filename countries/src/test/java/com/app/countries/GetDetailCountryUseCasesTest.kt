package com.app.countries

import com.app.base.data.network.BaseResource
import com.app.base.data.network.BaseResourceError
import com.app.countries.data.model.response.CountryDetailResponseDto
import com.app.countries.data.model.response.FlagsDto
import com.app.countries.data.model.response.NameDto
import com.app.countries.data.repository.CountryRepository
import com.app.countries.domain.useCases.GetDetailCountryUseCases
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class GetDetailCountryUseCasesTest {

    private val repository: CountryRepository = mockk()
    private lateinit var getDetailCountryUseCases: GetDetailCountryUseCases

    @Before
    fun setup() {
        getDetailCountryUseCases = GetDetailCountryUseCases(repository)
    }

    @Test
    fun `when repository returns success data, usecase should return Success resource`() = runBlocking {
        // GIVEN
        val countryName = "Mexico"
        // fill fake data
        val fakeDtoList = listOf(
            CountryDetailResponseDto(
                name = NameDto("Mexico", "UNITED STATED OF MEXICO", mapOf()),
                latlng = listOf(19.0, -99.0), // Aquí estaba el error del latlng
                flags = FlagsDto("","",""),
                area = 1246700L,
                cca2 = "CCA2",
                cca3 = "CCA3",
                flagEmoji = "bandera.jpg",
                currencies = mapOf(),
                languages = mapOf(),
                region = "America",
                subregion = "North America",
                capital = emptyList(),
                population = 130575786L
            )
        )

        // configure the repository mock to return the Retrofit response
        coEvery { repository.getDetailCountry(countryName) } returns Response.success(fakeDtoList)

        // WHEN
        val result = getDetailCountryUseCases(countryName)

        // THEN
        assertTrue("Expected Success but was $result", result is BaseResource.Success)
    }

    @Test
    fun `when repository returns null body, usecase should return ServerError`() = runBlocking {
        // GIVEN
        val countryName = "Unknown"
        // simulate a successful response with a null body
        coEvery { repository.getDetailCountry(countryName) } returns Response.success(null)

        // WHEN
        val result = getDetailCountryUseCases(countryName)

        // THEN
        assertTrue(result is BaseResource.Error)
        assertTrue((result as BaseResource.Error).error is BaseResourceError.ServerError)
    }

    @Test
    fun `when repository returns 401 error, usecase should return Unauthorized`() = runBlocking {
        // GIVEN
        val countryName = "SecretCountry"
        val errorResponse = Response.error<List<CountryDetailResponseDto>>(
            401, 
            "".toResponseBody(null)
        )
        coEvery { repository.getDetailCountry(countryName) } returns errorResponse

        // WHEN
        val result = getDetailCountryUseCases(countryName)

        // THEN
        assertTrue(result is BaseResource.Error)
        assertTrue((result as BaseResource.Error).error is BaseResourceError.Unauthorized)
    }

    @Test
    fun `when repository throws exception, usecase should return NetworkError`() = runBlocking {
        // GIVEN
        val countryName = "Any"
        coEvery { repository.getDetailCountry(countryName) } throws IOException()

        // WHEN
        val result = getDetailCountryUseCases(countryName)

        // THEN
        assertTrue(result is BaseResource.Error)
        assertTrue((result as BaseResource.Error).error is BaseResourceError.NetworkError)
    }
}