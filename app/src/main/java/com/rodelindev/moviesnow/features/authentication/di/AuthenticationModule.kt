package com.rodelindev.moviesnow.features.authentication.di

import com.rodelindev.moviesnow.features.authentication.data.matcher.EmailMatcherImpl
import com.rodelindev.moviesnow.features.authentication.data.network.AuthApi
import com.rodelindev.moviesnow.features.authentication.data.repository.AuthenticationRepositoryImpl
import com.rodelindev.moviesnow.features.authentication.domain.repository.AuthRepository
import com.rodelindev.moviesnow.features.authentication.domain.usecase.GetUserIdUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.IsUserLoggedInUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.LoginUseCases
import com.rodelindev.moviesnow.features.authentication.domain.usecase.LogoutUseCase
import com.rodelindev.moviesnow.features.authentication.domain.usecase.SignupUseCases
import com.rodelindev.moviesnow.features.authentication.presentation.login.LoginViewModel
import com.rodelindev.moviesnow.features.authentication.presentation.signup.SignupViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val authenticationModule = module {
    single<AuthApi> { get<Retrofit>().create<AuthApi>() }

    //Repository implementation
    factoryOf(::AuthenticationRepositoryImpl) { bind<AuthRepository>() }
    factoryOf(::EmailMatcherImpl)

    //Use cases
    factoryOf(::LoginUseCases)
    factoryOf(::SignupUseCases)
    factoryOf(::LogoutUseCase)
    factoryOf(::GetUserIdUseCase)
    factoryOf(::IsUserLoggedInUseCase)

    // View Models
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignupViewModel)
}
