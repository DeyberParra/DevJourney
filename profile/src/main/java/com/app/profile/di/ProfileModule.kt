package com.app.profile.di

import android.content.res.AssetManager
import com.app.profile.data.repository.ProfileRepository
import com.app.profile.domain.GetDevProfileUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileRepository(
        assetManager: AssetManager,
        gson: Gson
    ) : ProfileRepository{
        return ProfileRepository(assetManager, gson)
    }

    @Provides
    @Singleton
    fun provideGetProfileUseCase(profileRepository: ProfileRepository) : GetDevProfileUseCase{
        return GetDevProfileUseCase(profileRepository)
    }
}