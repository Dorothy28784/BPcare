package com.example.bpcare.data.repository

import com.example.bpcare.data.Model.UserModel

interface AuthService {

        suspend fun registerUser(user: UserModel)
        suspend fun loginUser(user: UserModel)
        suspend fun resetPassword(email: String)
        suspend fun getUserProfile(user: UserModel)
        suspend fun logoutUser()
        suspend fun getCurrentUserEmail(): String?
        suspend fun login(email: String, password: String)


}