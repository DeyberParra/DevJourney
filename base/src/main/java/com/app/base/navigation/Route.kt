package com.app.base.navigation// En módulo :base o :core
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable object Profile : Route
    @Serializable object Countries : Route
    @Serializable object CountriesDetail : Route
    @Serializable object Settings : Route // Tu tercera vista
}