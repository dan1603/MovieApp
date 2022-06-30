package com.kalashnyk.movietestapp.usecase

import com.kalashnyk.movietestapp.network.model.MovieListResponse
import com.kalashnyk.movietestapp.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class PopularMoviesUseCaseEvaluator(
    private val repo: MovieRepository
) : PopularMoviesUseCase {

    override suspend fun getPopularMovies(): Flow<MovieListResponse> =
        repo.getPopularMovies()
}
