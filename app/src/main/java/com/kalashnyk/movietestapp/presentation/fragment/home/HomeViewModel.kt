package com.kalashnyk.movietestapp.presentation.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalashnyk.movietestapp.network.model.MovieListResponse
import com.kalashnyk.movietestapp.usecase.PopularMoviesUseCase
import com.kalashnyk.movietestapp.util.Constants
import com.kalashnyk.movietestapp.util.ResultWrapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val popularMoviesFlow: PopularMoviesUseCase
) : ViewModel() {

    private val _popularMovies = MutableStateFlow(
        ResultWrapper.success(MovieListResponse.empty())
    )

    val popularMovies: StateFlow<ResultWrapper<MovieListResponse>> = _popularMovies

    init {
        viewModelScope.launch {
            popularMoviesFlow.getPopularMovies()
                .onStart {
                    _popularMovies.value = ResultWrapper.loading(null)
                }
                .catch {
                    val errorMessage = it.message ?: Constants.Error.ERROR_UNKNOWN
                    _popularMovies.value = ResultWrapper.error(errorMessage, null)
                }
                .collect {
                    _popularMovies.value = ResultWrapper.success(it)
                }
        }
    }
}
