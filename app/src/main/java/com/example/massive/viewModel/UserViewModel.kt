package com.example.massive.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.massive.model.UserModel
import com.example.massive.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(val repository: UserRepository): ViewModel() {
    private val _users = MutableLiveData<Response<UserModel>>()

    val users : LiveData<Response<UserModel>>
        get() = _users

    fun addUser(user: UserModel) {
        viewModelScope.launch {
            val response = repository.addUser(user)
            _users.value = response
        }
    }
}