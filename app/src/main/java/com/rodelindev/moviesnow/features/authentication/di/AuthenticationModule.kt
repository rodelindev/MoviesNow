package com.rodelindev.moviesnow.features.authentication.di

import com.rodelindev.moviesnow.features.authentication.data.matcher.EmailMatcherImpl
import com.rodelindev.moviesnow.features.authentication.data.network.IAuthApiService
import com.rodelindev.moviesnow.features.authentication.data.repository.AuthenticationRepositoryImpl
import com.rodelindev.moviesnow.features.authentication.domain.repository.IAuthRepository
import com.rodelindev.moviesnow.features.authentication.domain.usecase.GetUserIdUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.IsUserLoggedInUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.LoginUseCases
import com.rodelindev.moviesnow.features.authentication.domain.usecase.LogoutUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.SignupUseCases
import com.rodelindev.moviesnow.features.authentication.presentation.login.LoginViewModel
import com.rodelindev.moviesnow.features.authentication.presentation.signup.SignupViewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.factory
import org.koin.plugin.module.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.create

val authenticationModule = module {
    // Api Service retrofit
    single<IAuthApiService> { get<Retrofit>().create<IAuthApiService>() }

    // Repository implementation
    factory<AuthenticationRepositoryImpl>() bind(IAuthRepository::class)
    factory<EmailMatcherImpl>() //bind(EmailMatcher::class)

    // Use cases
    factory<LoginUseCases>()
    factory<SignupUseCases>()
    factory<LogoutUseCase>()
    factory<GetUserIdUseCase>()
    factory<IsUserLoggedInUseCase>()

    // View Models
    viewModel<LoginViewModel>()
    viewModel<SignupViewModel>()
}
