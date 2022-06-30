package com.kalashnyk.movietestapp.network.model

import com.google.gson.annotations.SerializedName

data class TrailerListResponse(
    @SerializedName("results")
    val result: List<TrailerResponse>,
)

data class TrailerResponse(
    @SerializedName("key")
    val keyUrl: String,
)
