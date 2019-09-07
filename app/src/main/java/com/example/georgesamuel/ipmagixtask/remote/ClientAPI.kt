package com.example.georgesamuel.ipmagixtask.remote

import androidx.lifecycle.LiveData
import com.example.georgesamuel.ipmagixtask.model.StudentResponse
import retrofit2.Call
import retrofit2.http.GET

interface ClientAPI {

    @GET("/ipmagix.studentsapi")
    fun getStudents() : Call<StudentResponse>
}