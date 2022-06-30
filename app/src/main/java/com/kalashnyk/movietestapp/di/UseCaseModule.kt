package com.kalashnyk.movietestapp.di

import androidx.annotation.Keep
import com.kalashnyk.movietestapp.usecase.*
import org.koin.dsl.module

@Keep
val useCaseModule = module {
    factory<MovieByIdUseCase> { MovieByIdUseCaseEvaluator(get()) }
    factory<PopularMoviesUseCase> { PopularMoviesUseCaseEvaluator(get()) }
    factory<TrailerByIdUseCase> { TrailerByIdUseCaseEvaluator(get()) }
}
