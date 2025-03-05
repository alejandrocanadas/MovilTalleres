package com.example.courseapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier



@Composable
fun AnimatedText(text: String) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color.Blue, Color.Green, Color.Cyan)
    )

    Box(
        modifier = Modifier.background(gradient)
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}
