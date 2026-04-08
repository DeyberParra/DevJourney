package com.app.profile.ui

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        val data = profileState
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Surface(
                shape = CircleShape,
                border = BorderStroke(6.dp, Color.White),
                shadowElevation = 12.dp
            ) {
                ProfileImage(data?.photo.orEmpty())
            }
            Spacer(modifier = Modifier.height(32.dp))
            HeroSection(data?.name.orEmpty(), data?.title.orEmpty())
            Spacer(modifier = Modifier.height(48.dp))
            ManifestoSection(manifest = data?.professional_summary.orEmpty())
            Spacer(modifier = Modifier.height(48.dp))
            ExperienceSection(experiences = data?.experience.orEmpty())
            Spacer(modifier = Modifier.height(48.dp))
            EducationAndSkillsSection(data?.skills.orEmpty() , education = data?.education.orEmpty())
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}



