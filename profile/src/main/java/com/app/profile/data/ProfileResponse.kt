package com.app.profile.data

data class ProfileResponse(val profile: ProfileData)

data class ProfileData(
    val name: String,
    val title: String,
    val professional_summary: String,
    val experience: List<WorkExperience>,
    val education: List<Education>
)

data class WorkExperience(val company: String, val role: String, val description: String)
data class Education(val degree: String, val institution: String, val year: String)