package com.app.countries.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.base.navigation.Route
import com.app.countries.ui.Greeting

fun NavGraphBuilder.countryScreen() {
    composable<Route.Countries> {
        //ProfileScreen()
        Greeting()
    }
}