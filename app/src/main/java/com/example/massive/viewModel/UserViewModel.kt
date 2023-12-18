package com.example.massive.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.massive.auth.AuthToken
import com.example.massive.auth.UserRequest
import com.example.massive.model.UserModel
import com.example.massive.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(val repository: UserRepository): ViewModel() {
    private val _users = MutableLiveData<Response<UserModel>>()
    private val _token = MutableLiveData<Response<AuthToken>>()

    val users : LiveData<Response<UserModel>>
        get() = _users

    val token : LiveData<Response<AuthToken>>
        get() = _token

    fun addUser(user: UserModel) {
        viewModelScope.launch {
            val response = repository.addUser(user)
            _users.value = response
        }
    }

    fun loginUser(user: UserRequest){
        viewModelScope.launch {
            val response = repository.loginUser(user)
            _token.postValue(response)
        }
    }
}