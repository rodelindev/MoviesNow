package com.rodelindev.moviesnow.features.profile.di

import com.rodelindev.moviesnow.features.profile.presentation.ProfileViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val profileModule = module {
    // View Models
    viewModel<ProfileViewModel>()
}