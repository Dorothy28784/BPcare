package com.example.bpcare.ui.Screens.Authentication.Login

import com.example.bpcare.data.repository.AuthRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun login(email: String, password: String) {

        if (email.isBlank() || password.isBlank()) {

            _message.value = "Email and password are required"
            return
        }

        viewModelScope.launch {

            _loading.value = true

            try {

                repository.login(email, password)

                _message.value = "Login successful"

                _isLoggedIn.value = true

            } catch (e: Exception) {

                _message.value = e.message ?: "Login failed"

                _isLoggedIn.value = false
            }

            _loading.value = false
        }
    }

    fun resetState() {

        _message.value = ""

        _isLoggedIn.value = false
    }

    fun logout() {
        viewModelScope.launch {
            try {
                repository.logoutUser()
                _isLoggedIn.value = false
                _message.value = "Logged out successfully"
            } catch (e: Exception) {
                _message.value = e.message ?: "Logout failed"
            }
        }
    }
}