package com.app.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.base.navigation.Route
import com.app.profile.ui.ProfileScreen

fun NavGraphBuilder.profileScreen(
) {
    composable<Route.Profile> {
        ProfileScreen()
    }
}