package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.jetpackintro.ui.theme.Jade
import com.example.jetpackintro.ui.theme.Pink

class SimpleAnimActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var sizeState by remember { mutableStateOf(200.dp) }
            val size by animateDpAsState(
                targetValue = sizeState ,
                tween(
                    durationMillis = 3000 ,
                    delayMillis = 100 , // anim delay
                    easing = androidx.compose.animation.core.FastOutSlowInEasing
                ), label = ""
//                spring(
//                    Spring.DampingRatioHighBouncy ,
//                    stiffness = Spring.StiffnessMediumLow
//                )
//                keyframes {
//                    durationMillis = 5000
//                    sizeState at 0 with LinearEasing
//                    sizeState * 1.5f at 1000 with FastOutSlowInEasing
//                    sizeState * 2f at 5000
//                }
            )

            val infiniteTransition = rememberInfiniteTransition(label = "")
            val color by infiniteTransition.animateColor(
                initialValue = Color.Red,
                targetValue = Color.Green,
                animationSpec = InfiniteRepeatableSpec(
                    tween(durationMillis = 2000),
                    repeatMode = androidx.compose.animation.core.RepeatMode.Reverse
                ),
                label = ""
            )

            Box(modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(16.dp)) // Adding rounded corners to the Box
                .background(color), // Apply background after clipping for rounded corners
            contentAlignment = Alignment.Center
            ){
                Button(onClick = {
                    sizeState += 50.dp
                } ,
                    shape = RoundedCornerShape(16.dp), // Adding rounded corners to the Button
                    colors = ButtonDefaults.buttonColors(Color.Transparent ),// Ensure background color is transparent to show gradient
                    modifier = Modifier
                        .wrapContentSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Blue,
                                    Color.Black
                                )
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Text( "Increase Size")
                }
            }
        }

        window.statusBarColor = android.graphics.Color.BLACK
    }

    private fun enableEdgeToEdge() {}
}

