package com.kalashnyk.movietestapp.repository

import com.kalashnyk.movietestapp.network.model.TrailerListResponse
import kotlinx.coroutines.flow.Flow

interface TrailerRepository {
    suspend fun getTrailerById(movieId: Int): Flow<TrailerListResponse>
}
