package com.example.georgesamuel.ipmagixtask.respository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.georgesamuel.ipmagixtask.model.StudentResponse
import com.example.georgesamuel.ipmagixtask.remote.ClientAPI
import com.example.georgesamuel.ipmagixtask.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsRepo(private val retrofitClient : RetrofitClient) {
    private val TAG = "StudentsRepo"
    private val clientAPI : ClientAPI = retrofitClient.getInstance()
    private val studentsList : MutableLiveData<StudentResponse> by lazy {
        MutableLiveData<StudentResponse>()
    }
    private val errorMessage : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun setStudents (){
        clientAPI.getStudents().enqueue(object : Callback<StudentResponse>{
            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

            override fun onResponse(call: Call<StudentResponse>, response: Response<StudentResponse>) {
                if(response.isSuccessful){
                    studentsList.postValue(response.body())
                }
                else{
                    errorMessage.postValue(response.message())
                }
            }

        })
    }

    fun getStudents() : LiveData<StudentResponse>{
        return studentsList
    }

    fun getError() : LiveData<String>{
        return errorMessage
    }
}