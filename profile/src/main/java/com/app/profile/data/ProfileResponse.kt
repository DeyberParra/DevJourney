package com.app.profile.data

data class ProfileResponse(val profile: ProfileData)

data class ProfileData(
    val name: String,
    val title: String,
    val photo : String,
    val professional_summary: String,
    val experience: List<WorkExperience>,
    val education: List<Education>,
    val skills: List<String>
)

data class WorkExperience(
    val company: String,
    val role: String,
    val description: String)
data class Education(val degree: String, val institution: String, val year: String)