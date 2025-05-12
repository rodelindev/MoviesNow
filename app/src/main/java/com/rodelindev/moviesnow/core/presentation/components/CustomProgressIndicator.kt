package com.rodelindev.moviesnow.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodelindev.moviesnow.ui.theme.MoviesNowTheme

@Composable
fun CustomProgressIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(54.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomProgressIndicatorPreview() {
    MoviesNowTheme {
        CustomProgressIndicator(
            modifier = Modifier.fillMaxSize()
        )
    }
}