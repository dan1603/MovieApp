package com.kalashnyk.movietestapp.usecase

import com.kalashnyk.movietestapp.network.model.TrailerListResponse
import kotlinx.coroutines.flow.Flow

interface TrailerByIdUseCase {
    suspend fun getTrailerById(movieId: Int): Flow<TrailerListResponse>
}
