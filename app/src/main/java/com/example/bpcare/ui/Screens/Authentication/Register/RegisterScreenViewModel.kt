package com.example.bpcare.ui.Screens.Authentication.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bpcare.data.Model.UserModel
import com.example.bpcare.data.repository.AuthRepository
import com.example.bpcare.data.repository.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegistrationUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

class RegistrationViewModel(
    private val authService: AuthService = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState

    fun registerUser(user: UserModel, confirmPass: String) {
        if (user.email.isBlank() || user.password.isBlank()) {
            _uiState.value = RegistrationUiState(errorMessage = "Fields cannot be empty")
            return
        }

        if (user.password != confirmPass) {
            _uiState.value = RegistrationUiState(errorMessage = "Passwords do not match")
            return
        }

        _uiState.value = RegistrationUiState(isLoading = true)

        viewModelScope.launch {
            try {

                val userToRegister = user.copy(password = user.password.lowercase())
                authService.registerUser(userToRegister)
                _uiState.value = RegistrationUiState(isSuccess = true)
            } catch (e: Exception) {
                _uiState.value = RegistrationUiState(errorMessage = "Registration failed: ${e.message}")
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}
