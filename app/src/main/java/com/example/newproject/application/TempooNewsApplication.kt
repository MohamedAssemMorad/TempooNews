package com.example.newproject.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TempooNewsApplication: Application() {
    // here using Koin we start our app and register module ands and repo and viewModels
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TempooNewsApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}