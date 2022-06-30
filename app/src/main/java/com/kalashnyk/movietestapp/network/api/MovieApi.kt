package com.kalashnyk.movietestapp.network.api

import com.kalashnyk.movietestapp.network.model.MovieItemResponse
import com.kalashnyk.movietestapp.network.model.MovieListResponse
import com.kalashnyk.movietestapp.network.model.TrailerListResponse
import com.kalashnyk.movietestapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET(Constants.Api.MOVIE_POPULAR)
    suspend fun getPopularMovies(): MovieListResponse

    @GET(Constants.Api.MOVIE)
    suspend fun getMovieById(
        @Path("movie_id") id: Int,
    ): MovieItemResponse

    @GET(Constants.Api.TRAILER)
    suspend fun getTrailerById(
        @Path("movie_id") id: Int,
    ): TrailerListResponse
}
