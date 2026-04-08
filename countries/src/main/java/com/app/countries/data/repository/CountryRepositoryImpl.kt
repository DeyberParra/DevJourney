package com.app.countries.data.repository

import com.app.countries.data.datasource.CountryLocalDataSource
import com.app.countries.data.model.response.CountryDetailResponseDto
import com.app.countries.data.service.CountryService
import com.app.countries.domain.model.CountryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Response
import java.io.InputStream
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val dataSource: CountryLocalDataSource,
    private val countryService: CountryService
) : CountryRepository {

    override suspend fun getLocalCountries(): List<CountryModel> = withContext(Dispatchers.IO) {
        val inputStream = dataSource.getCountriesInputStream()
        parseStringArrayXml(inputStream)
    }

    override suspend fun getDetailCountry(countryName: String): Response<List<CountryDetailResponseDto>> {
        return countryService.getDetailCountry(countryName)
    }

    private fun parseStringArrayXml(inputStream: InputStream): List<CountryModel> {
        val countries = mutableListOf<CountryModel>()
        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()

        inputStream.use { stream ->
            parser.setInput(stream, "UTF-8")
            var eventType = parser.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name

                if (eventType == XmlPullParser.START_TAG && tagName == "item") {
                    val countryName = parser.nextText()
                    if (countryName.isNotEmpty()) {
                        countries.add(CountryModel(name = countryName))
                    }
                }
                eventType = parser.next()
            }
        }
        return countries
    }
}