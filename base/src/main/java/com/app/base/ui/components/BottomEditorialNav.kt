package com.app.base.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import com.app.base.navigation.Route
import com.app.base.ui.theme.AppTheme

@Composable
fun BottomEditorialNav(
    navBackStackEntry: NavBackStackEntry?,
    onNavigateToProfile: () -> Unit = {},
    onNavigateToCountries: () -> Unit = {}
) {
    val isProfileSelected = navBackStackEntry?.destination?.hasRoute<Route.Profile>() == true
    val isCountriesSelected = navBackStackEntry?.destination?.hasRoute<Route.Countries>() == true
    val colorOptionProfile = if (isProfileSelected) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
    val colorOptionCountry = if (isCountriesSelected) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = AppTheme.dimensions.defaultPadding,
                vertical = AppTheme.dimensions.defaultPadding
            )
        ,
        shape = RoundedCornerShape(AppTheme.dimensions.roundedCornerShape),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = AppTheme.dimensions.cardElevation
    ) {
        Row(
            modifier = Modifier.padding(vertical = AppTheme.dimensions.rowPadding),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Item
            Surface(
                color = colorOptionProfile,
                shape = CircleShape,
                modifier = Modifier
                    .padding(horizontal = AppTheme.dimensions.defaultPadding, vertical = AppTheme.dimensions.miniPadding)
                    .clickable { onNavigateToProfile() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = AppTheme.dimensions.defaultPadding, vertical = AppTheme.dimensions.miniPadding),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.miniPadding)
                ) {
                    Icon(Icons.Default.AccountBox, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Text("Profile", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                }
            }

            Surface(
                color = colorOptionCountry,
                shape = CircleShape,
                modifier = Modifier
                    .padding(horizontal = AppTheme.dimensions.defaultPadding, vertical = AppTheme.dimensions.miniPadding)
                    .clickable { onNavigateToCountries() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = AppTheme.dimensions.defaultPadding, vertical = AppTheme.dimensions.miniPadding),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Default.Map, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Text("Country", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
