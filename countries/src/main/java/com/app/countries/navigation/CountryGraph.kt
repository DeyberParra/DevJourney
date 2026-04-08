package com.app.countries.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.app.base.navigation.Route
import com.app.countries.ui.detailCountry.DetailCountryScreen
import com.app.countries.ui.mainCountries.CountriesScreen

// graph to main countries section
fun NavGraphBuilder.countryScreen(
    navController: NavController
) {
    composable<Route.Countries> {
        CountriesScreen(navController)
    }
}

//graph to detail country selected
fun NavGraphBuilder.detailCountryScreen(
    onBackClick: () -> Unit
){
    composable<Route.CountriesDetail> { backStackEntry ->
        val detail = backStackEntry.toRoute<Route.CountriesDetail>()
        DetailCountryScreen(
            countryName = detail.countryName,
            onBackClick = onBackClick
        )
    }
}