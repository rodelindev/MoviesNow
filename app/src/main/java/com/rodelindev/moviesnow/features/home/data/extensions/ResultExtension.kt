package com.rodelindev.moviesnow.features.home.data.extensions

import kotlin.coroutines.cancellation.CancellationException

inline fun <T, R> T.resultOf(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}