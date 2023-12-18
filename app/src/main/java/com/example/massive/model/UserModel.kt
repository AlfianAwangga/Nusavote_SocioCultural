package com.example.massive.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    val date_birth: String,
    val fullname: String,
    val username: String,
    val roles: String,
    @SerializedName("re-password")
    val konfirmasi: String,
    val password: String
)
