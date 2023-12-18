package com.example.massive.repository

import com.example.massive.api.ApiClient
import com.example.massive.auth.AuthToken
import com.example.massive.auth.UserRequest
import com.example.massive.model.UserModel
import retrofit2.Response

class UserRepository {
    suspend fun addUser(user: UserModel): Response<UserModel> {
        return ApiClient.apiService.addUser(user)
    }
    suspend fun loginUser(user: UserRequest): Response<AuthToken>{
        return ApiClient.apiService.loginUser(user)
    }
}