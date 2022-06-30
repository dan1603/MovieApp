package com.kalashnyk.movietestapp.util

object Constants {

    object Api {
        const val MOVIE_POPULAR = "3/movie/popular"
        const val MOVIE = "3/movie/{movie_id}"
        const val TRAILER = "3/movie/{movie_id}/videos"

        const val QUERY_API_KEY = "api_key"
    }

    object Url {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val YOUTUBE_URL = "https://www.youtube.com/watch?v="
        const val API_KEY = "3a85880839bd62582a40fb4bca84e26e"
    }

    object Base {
        const val BASE_TRAILER_URL = "dQw4w9WgXcQ"
        const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    }

    object Error {
        const val ERROR_UNKNOWN = "Unknown error"
    }
}
