package com.kalashnyk.movietestapp.usecase

import com.kalashnyk.movietestapp.network.model.MovieListResponse
import kotlinx.coroutines.flow.Flow

interface PopularMoviesUseCase {
    suspend fun getPopularMovies(): Flow<MovieListResponse>
}
