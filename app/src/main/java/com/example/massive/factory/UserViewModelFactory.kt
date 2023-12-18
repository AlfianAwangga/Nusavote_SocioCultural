package com.example.massive.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.massive.repository.UserRepository
import com.example.massive.viewModel.UserViewModel

class UserViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}