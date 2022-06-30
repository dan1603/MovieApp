package com.kalashnyk.movietestapp.util

data class ResultWrapper<out T>(
    val status: Status,
    val data: T?,
    val error: Error?,
    val message: String?
) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): ResultWrapper<T> =
            ResultWrapper(Status.SUCCESS, data, null, null)

        fun <T> error(message: String, error: Error?): ResultWrapper<T> =
            ResultWrapper(Status.ERROR, null, error, message)

        fun <T> loading(data: T? = null): ResultWrapper<T> =
            ResultWrapper(Status.LOADING, data, null, null)
    }
}
