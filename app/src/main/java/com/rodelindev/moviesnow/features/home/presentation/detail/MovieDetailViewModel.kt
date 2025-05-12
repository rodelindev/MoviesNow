package com.rodelindev.moviesnow.features.home.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rodelindev.moviesnow.features.home.domain.usecase.GetMovieByIdUseCase
import com.rodelindev.moviesnow.navigation.NavigationRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MovieDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailState())
    val state: StateFlow<MovieDetailState> = _state.asStateFlow()

    init {
        val movie = savedStateHandle.toRoute<NavigationRoute.MovieDetail>()
        getMovieById(movieId = movie.id)
    }

    private fun getMovieById(movieId: Int) {
        _state.update { uiState ->
            uiState.copy(isLoading = true)
        }
        viewModelScope.launch {
            getMovieByIdUseCase(movieId = movieId).onSuccess { movie ->
                _state.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        movie = movie
                    )
                }
            }.onFailure {
                _state.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        error = it.message
                    )
                }
            }
        }
    }
}