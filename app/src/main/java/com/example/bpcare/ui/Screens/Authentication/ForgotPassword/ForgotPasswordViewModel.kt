package com.example.bpcare.ui.Screens.Authentication.ForgotPassword

import com.example.bpcare.data.repository.AuthRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun resetPassword(email: String) {

        if (email.isBlank()) {
            _message.value = "Email is required"
            return
        }

        viewModelScope.launch {

            _loading.value = true

            try {

                repository.resetPassword(email)

                _message.value = "Check your email for reset link"

            } catch (e: Exception) {

                _message.value = e.message ?: "Error occurred"
            }

            _loading.value = false
        }
    }
}