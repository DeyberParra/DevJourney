package com.app.countries.data.model.response

data class CountryDetailModel(
    val name: NameModel,
    val area : Long,
    val cca2: String,
    val cca3: String,
    val flagEmoji: String,
    val flags: FlagsModel,
    val currencies: Map<String, CurrencyModel>?,
    val languages: Map<String, String>?,
    val region: String,
    val subregion: String?,
    val capital: List<String>?,
    val population: Long,
    val latlng: List<Double>
)

data class NameModel(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeNameModel>?
)

data class NativeNameModel(
    val official: String,
    val common: String
)

data class CurrencyModel(
    val name: String,
    val symbol: String?
)

data class FlagsModel(
    val png: String = "",
    val svg: String = "",
    val alt: String? = ""
)