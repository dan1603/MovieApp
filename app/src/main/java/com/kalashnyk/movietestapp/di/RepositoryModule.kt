package com.kalashnyk.movietestapp.di

import androidx.annotation.Keep
import com.kalashnyk.movietestapp.repository.MovieRepository
import com.kalashnyk.movietestapp.repository.MovieRepositoryProvider
import com.kalashnyk.movietestapp.repository.TrailerRepository
import com.kalashnyk.movietestapp.repository.TrailerRepositoryProvider
import org.koin.dsl.module

@Keep
val repositoryModule = module {
    factory<MovieRepository> { MovieRepositoryProvider(get()) }
    factory<TrailerRepository> { TrailerRepositoryProvider(get()) }
}
