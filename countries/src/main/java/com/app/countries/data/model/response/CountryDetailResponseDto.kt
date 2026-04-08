package com.app.countries.data.model.response

import com.google.gson.annotations.SerializedName

data class CountryDetailResponseDto(
    @SerializedName("name") val name: NameDto,
    @SerializedName("area") val area: Long,
    @SerializedName("cca2") val cca2: String,
    @SerializedName("cca3") val cca3: String,
    @SerializedName("flag") val flagEmoji: String,
    @SerializedName("flags") val flags: FlagsDto,
    @SerializedName("currencies") val currencies: Map<String, CurrencyDto>?,
    @SerializedName("languages") val languages: Map<String, String>?,
    @SerializedName("region") val region: String,
    @SerializedName("subregion") val subregion: String?,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("population") val population: Long,
    @SerializedName("latlng") val latlng: List<Double>
)

data class NameDto(
    @SerializedName("common") val common: String,
    @SerializedName("official") val official: String,
    @SerializedName("nativeName") val nativeName: Map<String, NativeNameDto>?
)

data class NativeNameDto(
    @SerializedName("official") val official: String,
    @SerializedName("common") val common: String
)

data class CurrencyDto(
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String?
)

data class FlagsDto(
    @SerializedName("png") val png: String,
    @SerializedName("svg") val svg: String,
    @SerializedName("alt") val alt: String?
)