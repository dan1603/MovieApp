package com.kalashnyk.movietestapp.di

import androidx.annotation.Keep
import com.kalashnyk.movietestapp.network.api.MovieApi
import com.kalashnyk.movietestapp.network.interceptor.RequestInterceptor
import com.kalashnyk.movietestapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Keep
val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single<Retrofit.Builder> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
    }

    single<MovieApi> {
        get<Retrofit.Builder>()
            .baseUrl(Constants.Url.BASE_URL)
            .build()
            .create(MovieApi::class.java)
    }
}
