package com.app.profile.ui.mainProfileScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.base.ui.BaseScreen
import com.app.base.ui.theme.AppTheme
import com.app.profile.ui.viewModel.ProfileViewModel

/**
 *@author : DeyberParra
 *@description : Main Profile Screen*/
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }
    BaseScreen(viewModel) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            val data = profileState
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = AppTheme.dimensions.medPadding),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacerLarge))
                Surface(
                    shape = CircleShape,
                    border = BorderStroke(AppTheme.dimensions.borderStrokerDefault, Color.White),
                    shadowElevation = AppTheme.dimensions.shadowElevationDefault
                ) {
                    ProfileImage(data?.photo.orEmpty())
                }
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacerLarge))
                HeroSection(data?.name.orEmpty(), data?.title.orEmpty())
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacerLarge))
                ManifestoSection(manifest = data?.professional_summary.orEmpty())
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacerLarge))
                ExperienceSection(experiences = data?.experience.orEmpty())
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacerLarge))
                EducationAndSkillsSection(data?.skills.orEmpty() , education = data?.education.orEmpty())
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacerExtraLarge))
            }
        }
    }

}



