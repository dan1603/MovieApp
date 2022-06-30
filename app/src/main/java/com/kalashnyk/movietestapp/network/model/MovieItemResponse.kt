package com.kalashnyk.movietestapp.network.model

import com.google.gson.annotations.SerializedName

data class MovieItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val poster: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("video")
    val video: Boolean,
) {
    companion object {
        fun empty() = MovieItemResponse(
            id = 0,
            poster = "",
            title = "",
            overview = "",
            voteAverage = 0.0,
            video = false,
        )
    }
}
