package com.rodelindev.moviesnow.features.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rodelindev.moviesnow.features.home.domain.model.Movie
import com.rodelindev.moviesnow.features.home.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val state: StateFlow<PagingData<Movie>> = _state
        .onStart {
            getCharacters()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PagingData.empty()
        )

    private fun getCharacters() {
        viewModelScope.launch {
            getMoviesUseCase()
                .cachedIn(viewModelScope)
                .collect { movie ->
                    _state.value = movie
                }
        }
    }
}
