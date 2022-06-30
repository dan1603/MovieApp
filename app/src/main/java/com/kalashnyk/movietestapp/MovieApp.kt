package com.kalashnyk.movietestapp

import android.app.Application
import com.kalashnyk.movietestapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
