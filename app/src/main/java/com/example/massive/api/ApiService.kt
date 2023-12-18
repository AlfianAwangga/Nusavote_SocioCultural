package com.example.massive.api

import com.example.massive.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/register/create")
    suspend fun addUser(
        @Body post: UserModel
    ): Response<UserModel>
}