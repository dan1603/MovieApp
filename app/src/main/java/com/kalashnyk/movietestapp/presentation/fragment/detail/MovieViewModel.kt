package com.kalashnyk.movietestapp.presentation.fragment.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalashnyk.movietestapp.network.model.MovieItemResponse
import com.kalashnyk.movietestapp.network.model.TrailerResponse
import com.kalashnyk.movietestapp.usecase.MovieByIdUseCase
import com.kalashnyk.movietestapp.usecase.TrailerByIdUseCase
import com.kalashnyk.movietestapp.util.Constants
import com.kalashnyk.movietestapp.util.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieByIdFlow: MovieByIdUseCase,
    private val trailerByIdFlow: TrailerByIdUseCase
) : ViewModel() {

    private val _movieDetails = MutableStateFlow(
        ResultWrapper.success(MovieItemResponse.empty())
    )

    private val _lastTrailer = MutableStateFlow(
        ResultWrapper.success(TrailerResponse(Constants.Base.BASE_TRAILER_URL))
    )

    val movieDetails: StateFlow<ResultWrapper<MovieItemResponse>> = _movieDetails
    val lastTrailer: StateFlow<ResultWrapper<TrailerResponse>> = _lastTrailer

    fun fetchMovieById(movieId: Int) {
        viewModelScope.launch {
            movieByIdFlow.getMovieById(movieId)
                .onStart {
                    _movieDetails.value = ResultWrapper.loading(null)
                }
                .catch {
                    val errorMessage = it.message ?: Constants.Error.ERROR_UNKNOWN
                    _movieDetails.value = ResultWrapper.error(errorMessage, null)
                }
                .collect {
                    _movieDetails.value = ResultWrapper.success(it)
                }
        }
    }

    fun fetchMovieTrailer(movieId: Int) {
        viewModelScope.launch {
            trailerByIdFlow.getTrailerById(movieId)
                .onStart {
                    _lastTrailer.value = ResultWrapper.loading(null)
                }
                .catch {
                    val errorMessage = it.message ?: Constants.Error.ERROR_UNKNOWN
                    _lastTrailer.value = ResultWrapper.error(errorMessage, null)
                }
                .collect {
                    _lastTrailer.value = ResultWrapper.success(it.result.last())
                }
        }
    }
}
