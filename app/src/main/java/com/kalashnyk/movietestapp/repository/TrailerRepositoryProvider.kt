package com.kalashnyk.movietestapp.repository

import com.kalashnyk.movietestapp.network.api.MovieApi
import com.kalashnyk.movietestapp.network.model.TrailerListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TrailerRepositoryProvider(private val api: MovieApi) : TrailerRepository {

    override suspend fun getTrailerById(movieId: Int): Flow<TrailerListResponse> = flow {
        val trailers = api.getTrailerById(movieId)
        emit(trailers)
    }.flowOn(Dispatchers.IO)
}
