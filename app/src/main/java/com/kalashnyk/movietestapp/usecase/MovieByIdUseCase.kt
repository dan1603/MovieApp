package com.kalashnyk.movietestapp.usecase

import com.kalashnyk.movietestapp.network.model.MovieItemResponse
import kotlinx.coroutines.flow.Flow

interface MovieByIdUseCase {
    suspend fun getMovieById(movieId: Int): Flow<MovieItemResponse>
}
