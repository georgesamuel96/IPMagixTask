package com.example.georgesamuel.ipmagixtask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.georgesamuel.ipmagixtask.respository.StudentsRepo

class ViewModelFactory(private val repo : StudentsRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentsViewModel(repo) as T
    }
}