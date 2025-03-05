package com.example.courseapp.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun AnimatedBackground() {
    val colors = listOf(Color.Blue, Color.Cyan, Color.Magenta)

    val transition = rememberInfiniteTransition()
    val offset by transition.animateFloat(
        label = "OffsetAnimation",
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000),
            repeatMode = RepeatMode.Reverse
        )
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = colors,
                    start = Offset(offset, 0f),
                    end = Offset(0f, offset)
                )
            )
    )
}
