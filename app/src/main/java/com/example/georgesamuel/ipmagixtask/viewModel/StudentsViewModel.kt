package com.example.georgesamuel.ipmagixtask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.georgesamuel.ipmagixtask.model.StudentResponse
import com.example.georgesamuel.ipmagixtask.respository.StudentsRepo

class StudentsViewModel(private val repo : StudentsRepo) : ViewModel() {
    private val TAG = "StudentsViewModel"
    private var studentsRepo: StudentsRepo = repo

    init {
        studentsRepo.setStudents()
    }

    fun getStudents(): LiveData<StudentResponse>? {
        return studentsRepo.getStudents()
    }

    fun getError() : LiveData<String>?{
        return studentsRepo.getError()
    }
}