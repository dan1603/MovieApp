package com.kalashnyk.movietestapp.di

import androidx.annotation.Keep
import com.kalashnyk.movietestapp.presentation.fragment.home.HomeViewModel
import com.kalashnyk.movietestapp.presentation.fragment.detail.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Keep
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MovieViewModel(get(), get()) }
}
