package com.kalashnyk.movietestapp.usecase

import com.kalashnyk.movietestapp.network.model.TrailerListResponse
import com.kalashnyk.movietestapp.repository.TrailerRepository
import kotlinx.coroutines.flow.Flow

class TrailerByIdUseCaseEvaluator(
    private val repo: TrailerRepository
) : TrailerByIdUseCase {

    override suspend fun getTrailerById(movieId: Int): Flow<TrailerListResponse> =
        repo.getTrailerById(movieId)

}
