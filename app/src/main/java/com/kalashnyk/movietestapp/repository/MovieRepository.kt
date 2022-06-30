package com.kalashnyk.movietestapp.repository

import com.kalashnyk.movietestapp.network.model.MovieItemResponse
import com.kalashnyk.movietestapp.network.model.MovieListResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(): Flow<MovieListResponse>

    suspend fun getMovieById(movieId: Int): Flow<MovieItemResponse>
}
