package com.example.georgesamuel.ipmagixtask

import android.app.Application
import com.example.georgesamuel.ipmagixtask.remote.RetrofitClient
import com.example.georgesamuel.ipmagixtask.respository.StudentsRepo
import com.example.georgesamuel.ipmagixtask.viewModel.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class StudentsApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind<RetrofitClient>() with singleton { RetrofitClient }
        bind<StudentsRepo>() with provider { StudentsRepo(instance()) }
        bind<ViewModelFactory>() with provider { ViewModelFactory(instance()) }
    }
}