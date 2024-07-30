package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CircularProgressBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressBar(percentage = 0.8f, number = 100 , strokeWidth = 12.dp , radius = 60.dp)
            }
        }
    }

    @Composable
    fun CircularProgressBar(
        percentage: Float,
        number: Int,
        fontSize: TextUnit = 28.sp,
        radius: Dp = 50.dp,
        color: Color = Color.Green,
        strokeWidth: Dp = 8.dp,
        animDuration: Int = 1000,
        animDelay: Int = 0,
    ) {
        var animationPlayed by remember {
            mutableStateOf(false)
        }
        val currentPercentage = animateFloatAsState(
            targetValue = if (animationPlayed) percentage else 0f,
            animationSpec = tween(
                delayMillis = animDelay,
                durationMillis = animDuration
            )
        )

        LaunchedEffect(key1 = true) {  //only gets triggered for first composition
            animationPlayed = true
        }

        Box(
            modifier = Modifier.size(radius * 2f),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(radius * 2f)) {
                drawArc(
                    color = color,
                    -90f,
                    360 * currentPercentage.value,
                    useCenter = false,  // no pie
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }

            Text(
                text = (currentPercentage.value * number).toInt().toString(),
                color = Color.Black,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}