package com.rodelindev.moviesnow.features.profile.presentation

sealed interface ProfileEvent {
    data class OnUserNameChange(val message: String) : ProfileEvent
}
