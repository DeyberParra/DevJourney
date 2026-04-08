package com.app.base.navigation
import kotlinx.serialization.Serializable


/**
 * @author : DeyberParra
 * @description: Defines the different navigation routes, as well as the parameters expected by each route
 * */
@Serializable
sealed interface Route {
    @Serializable
    data object Profile : Route

    @Serializable
    data object Countries : Route

    @Serializable
    data class CountriesDetail(val countryName: String) : Route
}