package com.kalashnyk.movietestapp.network.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val result: List<MovieItemResponse>,
) {
    companion object {
        fun empty() = MovieListResponse(0, emptyList())
    }
}
