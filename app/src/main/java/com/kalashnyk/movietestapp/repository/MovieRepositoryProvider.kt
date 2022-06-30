package com.kalashnyk.movietestapp.repository

import com.kalashnyk.movietestapp.network.api.MovieApi
import com.kalashnyk.movietestapp.network.model.MovieItemResponse
import com.kalashnyk.movietestapp.network.model.MovieListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryProvider(private val api: MovieApi) : MovieRepository {

    override suspend fun getPopularMovies(): Flow<MovieListResponse> = flow {
        val moviesPopular = api.getPopularMovies()
        emit(moviesPopular)
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieById(movieId: Int): Flow<MovieItemResponse> = flow {
        val movie = api.getMovieById(movieId)
        emit(movie)
    }.flowOn(Dispatchers.IO)
}
