package com.rodelindev.moviesnow.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodelindev.moviesnow.features.authentication.domain.usecase.GetUserIdUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.LogoutUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    var isLoggedIn by mutableStateOf(true)
        private set

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}
