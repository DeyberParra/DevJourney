package com.app.profile.data.repository

import android.content.res.AssetManager
import com.app.profile.data.ProfileData
import com.app.profile.data.ProfileResponse
import com.google.gson.Gson
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val assetManager: AssetManager,
    private val gson: Gson
) {
    fun getLocalProfile(): ProfileData? {
        return try {
            val jsonString = assetManager.open("dev_profile.json")
                .bufferedReader()
                .use { it.readText() }
            
            val response = gson.fromJson(jsonString, ProfileResponse::class.java)
            response.profile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}