package com.app.base.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object Profile : Route

    @Serializable
    data object Countries : Route

    @Serializable
    data class CountriesDetail(val countryName: String) : Route
}