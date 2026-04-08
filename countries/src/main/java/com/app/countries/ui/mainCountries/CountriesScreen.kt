package com.app.countries.ui.mainCountries

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.base.navigation.Route
import com.app.base.ui.BaseScreen
import com.app.base.ui.theme.AppTheme
import com.app.countries.R
import com.app.countries.ui.viewModel.CountriesViewModel

/**
* @author : DeyberParra
 * @description : main country section*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    navController: NavController,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val countries by viewModel.filteredCountries.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCountries()
    }

    BaseScreen(viewModel) { // here apply baseScreen
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(R.string.countries_title), style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold))
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(padding)
                    .padding(horizontal = AppTheme.dimensions.defaultPadding)
            ) {
                // finder
                item {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { viewModel.onSearchQueryChanged(it) },
                        placeholder = { Text(stringResource(R.string.countries_search_country_title)) },
                        leadingIcon = { Icon(Icons.Default.Search, null) },
                        modifier = Modifier.fillMaxWidth().padding(vertical = AppTheme.dimensions.defaultPadding),
                        shape = CircleShape,
                    )
                }
                // countries List
                items(countries, key = { it.name.toString()}) { country ->
                    CountryItem(
                        countryName = country.name.orEmpty(),
                        onCountryClick = { selected ->
                            navController.navigate(Route.CountriesDetail(countryName = selected))
                        }
                    )
                }

                item { Spacer(modifier = Modifier.height(AppTheme.dimensions.spacerExtraLarge)) }
            }
        }
    }
}