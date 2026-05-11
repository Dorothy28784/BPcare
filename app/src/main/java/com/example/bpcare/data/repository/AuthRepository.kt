package com.example.bpcare.data.repository

import com.example.bpcare.data.Model.UserModel

class AuthRepository : AuthService {

    override suspend fun registerUser(user: UserModel) {
        // Mock registration logic
    }

    override suspend fun loginUser(user: UserModel) {
        // Mock login logic
    }

    override suspend fun login(email: String, password: String) {
        // Mock login logic
    }

    override suspend fun resetPassword(email: String) {
        // Mock reset password logic
    }

    override suspend fun getUserProfile(user: UserModel) {
        // Mock get user profile logic
    }

    override suspend fun getCurrentUserEmail(): String? {
        return "mock@example.com"
    }

    override suspend fun logoutUser() {
        // Mock logout logic
    }
}
