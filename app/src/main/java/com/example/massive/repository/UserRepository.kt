package com.example.massive.repository

import com.example.massive.api.ApiClient
import com.example.massive.model.UserModel
import retrofit2.Response

class UserRepository {
    suspend fun addUser(user: UserModel): Response<UserModel> {
        return ApiClient.apiService.addUser(user)
    }
}