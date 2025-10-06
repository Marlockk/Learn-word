package com.example.newapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newapp.domain.useCases.QuizeInteractor


class MyViewModelFactory(private val quizeInteractor: QuizeInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(quizeInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
