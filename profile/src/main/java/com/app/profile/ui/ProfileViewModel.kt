package com.app.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.base.data.network.BaseResource
import com.app.profile.data.ProfileData
import com.app.profile.data.repository.ProfileRepository
import com.app.profile.domain.GetDevProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getDevProfileUseCase: GetDevProfileUseCase
) : ViewModel() {

    private val _profileState = MutableStateFlow<ProfileData?>(null)
    val profileState = _profileState.asStateFlow()

    fun loadProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getDevProfileUseCase.invoke()
            when(data){
                is BaseResource.Success ->{
                    println("deyber:success: ${data.data}")
                }
                is BaseResource.Error -> {
                    println("deyber:error: ${data.error}")
                }

                is BaseResource.Loading ->{

                }
            }
        }
    }
}