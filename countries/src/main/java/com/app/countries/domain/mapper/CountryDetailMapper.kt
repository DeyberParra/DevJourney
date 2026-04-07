package com.app.countries.domain.mapper

import com.app.countries.data.model.response.CountryDetailResponseDto
import com.app.countries.data.model.response.CurrencyDto
import com.app.countries.data.model.response.FlagsDto
import com.app.countries.data.model.response.NameDto
import com.app.countries.data.model.response.NativeNameDto
import com.app.countries.domain.model.CountryDetailModel
import com.app.countries.domain.model.CurrencyModel
import com.app.countries.domain.model.FlagsModel
import com.app.countries.domain.model.NameModel
import com.app.countries.domain.model.NativeNameModel


fun CountryDetailResponseDto.toModel() : CountryDetailModel{
    return CountryDetailModel(
        name = name.toModel(),
        cca2 = cca2,
        cca3 = cca3,
        flagEmoji = flagEmoji,
        flags = flags.toModel(),
        currencies = currencies?.mapValues { it.value.toModel() },
        languages = languages,
        region = region,
        subregion = subregion,
        capital = capital,
        population = population,
        latlng = latlng
    )
}

fun NameDto.toModel() : NameModel {
    return NameModel(
        common = this.common,
        official = this.official,
        nativeName = this.nativeName?.mapValues { it.value.toModel() }
    )
}

fun NativeNameDto.toModel(): NativeNameModel {
    return NativeNameModel(
        official  = this.official,
        common = this.common
    )
}

fun CurrencyDto.toModel() : CurrencyModel {
    return CurrencyModel(
        name = name,
        symbol = symbol
    )
}

fun FlagsDto.toModel() : FlagsModel {
    return FlagsModel(
        png = png,
        svg = svg,
        alt = alt
    )
}