package com.app.countries.ui.detailCountry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.base.ui.BaseScreen
import com.app.countries.R
import com.app.countries.data.model.response.CountryDetailModel
import com.app.countries.ui.viewModel.CountriesViewModel

@Composable
fun DetailCountryScreen(
    countryName: String,
    onBackClick: () -> Unit,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val detailState by viewModel.detailState.collectAsStateWithLifecycle()

    LaunchedEffect(countryName) {
        viewModel.getCountryDetail(countryName)
    }

    BaseScreen(viewModel) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                CountryDetailTopBar(countryName, onBackClick)
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                detailState?.let { country ->
                    CountryDetailContent(country = country)
                }
            }
        }
    }

}

@Composable
private fun CountryDetailContent(country: CountryDetailModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        AsyncImage(
            model = country.flags.png,
            contentDescription = "Flag of ${country.name}",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = country.name.official,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp,
                lineHeight = 40.sp
            ),
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = country.name.common,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Light),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            InfoDataBlock(
                label = stringResource(R.string.population_title),
                value = formatNumber(country.population),
                modifier = Modifier.weight(1f)
            )
            InfoDataBlock(
                label = stringResource(R.string.area_title),
                value = formatNumber(country.area),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            InfoDataBlock(
                label = stringResource(R.string.region_title),
                value = country.region,
                modifier = Modifier.weight(1f)
            )
            InfoDataBlock(
                label = stringResource(R.string.subregion_title),
                value = country.subregion.orEmpty(),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
private fun InfoDataBlock(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(end = 16.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            ),
            color = MaterialTheme.colorScheme.tertiary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CountryDetailTopBar(title: String, onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold, letterSpacing = 3.sp)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

private fun formatNumber(number: Long?): String {
    return number?.let {
        java.text.NumberFormat.getInstance(java.util.Locale.getDefault()).format(it)
    } ?: "N/A"
}