package com.app.base.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun showLoading() { _isLoading.value = true }
    fun hideLoading() { _isLoading.value = false }
    
    fun showError(message: String) {
        _error.value = message
    }

    fun clearError() {
        _error.value = null
    }
}