package com.example.courseapp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay

@Composable
fun UserImageWithBorder(imageUrl: String) {
    var animatedColor by remember { mutableStateOf(Color.Red) }

    val borderColor by animateColorAsState(
        targetValue = animatedColor,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )



    LaunchedEffect(Unit) {
        while (true) {
            animatedColor = if (animatedColor == Color.Red) Color.Blue else Color.Red
            delay(1000)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(120.dp)
            .shadow(8.dp, CircleShape)
            .background(MaterialTheme.colorScheme.background, CircleShape)
            .border(BorderStroke(4.dp, borderColor), CircleShape)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "Imagen del usuario",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
    }
}
