package com.rodelindev.moviesnow.features.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}