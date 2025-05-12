package com.rodelindev.moviesnow.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rodelindev.moviesnow.home.domain.model.Movie
import com.rodelindev.moviesnow.home.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val state: StateFlow<PagingData<Movie>> = _state.asStateFlow()

    init {
        getCharacters()
    }

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