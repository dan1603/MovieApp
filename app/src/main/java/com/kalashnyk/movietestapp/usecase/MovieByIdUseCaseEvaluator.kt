package com.kalashnyk.movietestapp.usecase

import com.kalashnyk.movietestapp.network.model.MovieItemResponse
import com.kalashnyk.movietestapp.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieByIdUseCaseEvaluator(
    private val repo: MovieRepository
) : MovieByIdUseCase {

    override suspend fun getMovieById(movieId: Int): Flow<MovieItemResponse> =
        repo.getMovieById(movieId)

}
