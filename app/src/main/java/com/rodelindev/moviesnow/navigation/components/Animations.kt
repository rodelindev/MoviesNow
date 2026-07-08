package com.rodelindev.moviesnow.navigation.components

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith

// Enter transition
val enterTransitionSpec: () -> ContentTransform = {
    slideInHorizontally(
        animationSpec = tween(500)
    ) + fadeIn() togetherWith slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(500)
    ) + fadeOut()
}

// Exit transition
val popTransitionSpec: () -> ContentTransform = {
    slideInHorizontally(
        animationSpec = tween(500)
    ) + fadeIn() togetherWith slideOutHorizontally(
        animationSpec = tween(500)
    ) + fadeOut()
}
