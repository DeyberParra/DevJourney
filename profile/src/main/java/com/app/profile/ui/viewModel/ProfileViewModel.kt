package com.app.profile.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.app.base.data.network.BaseResource
import com.app.base.ui.BaseViewModel
import com.app.profile.data.ProfileData
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
) : BaseViewModel() {

    private val _profileState = MutableStateFlow<ProfileData?>(null)
    val profileState = _profileState.asStateFlow()

    fun loadProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading()
            val response = getDevProfileUseCase.invoke()
            when(response){
                is BaseResource.Success ->{
                    _profileState.value = response.data
                    hideLoading()
                }
                is BaseResource.Error -> {
                    showError(response.error.message)
                    hideLoading()
                }

            }
        }
    }
}