package com.app.profile.domain

import com.app.base.data.network.BaseResource
import com.app.base.data.network.BaseResourceError
import com.app.profile.data.ProfileData
import com.app.profile.data.repository.ProfileRepository
import javax.inject.Inject

class GetDevProfileUseCase @Inject constructor(
    val profileRepository: ProfileRepository
) {
    suspend operator fun invoke() : BaseResource<ProfileData?>{
        return try {
            val profile = profileRepository.getLocalProfile()
            BaseResource.Success(profile)
        } catch (e : Exception){
            BaseResource.Error(BaseResourceError.UnknownError(e))
        }
    }
}