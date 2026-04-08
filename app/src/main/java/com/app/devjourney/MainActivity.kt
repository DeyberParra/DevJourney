package com.app.devjourney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.app.base.navigation.Route
import com.app.base.ui.components.BottomEditorialNav
import com.app.base.ui.theme.DevJourneyTheme
import com.app.countries.navigation.countryScreen
import com.app.profile.navigation.profileScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DevJourneyTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomEditorialNav(
                            onNavigateToProfile = {
                                navController.navigate(Route.Profile) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            onNavigateToCountries = {
                                navController.navigate(Route.Countries) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.Profile,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        profileScreen(onNavigateToCountries = {
                            navController.navigate(Route.Countries)
                        })
                        countryScreen()
                    }
                }
            }
        }
    }
}
