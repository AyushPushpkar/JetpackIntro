package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DropdownActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                color = Color.Black,
                modifier = Modifier.fillMaxSize()
            ) {
                Dropdown(
                    Text = "Hello World ! " ,
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(
                        text = "This is now Revealed !" ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.Green)
                            .padding(10.dp),
                        textAlign = TextAlign.Center ,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
    private fun enableEdgeToEdge(){}
}

@Composable
fun Dropdown(
    Text : String ,
    modifier: Modifier = Modifier ,
    initiallyOpened : Boolean = false ,
    content : @Composable () -> Unit
){
    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }
    val alpha = animateFloatAsState(          // fade in fade out
        targetValue = if (isOpen) 1f else 0f ,
        animationSpec = tween(
            durationMillis = 300
        )
    )
    val rotatex = animateFloatAsState(        // 3d rotation along x axis
        targetValue = if (isOpen) 0f else -90f ,
        animationSpec = tween(
            durationMillis = 300
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = Text ,
                color = Color.White,
                fontSize = 16.sp
            )

            Icon(                    // inbuilt icon
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "DropDown" ,
                tint = Color.White ,
                modifier = Modifier
                    .clickable {
                        isOpen = !isOpen
                    }
                    .scale(1f, if (isOpen) -1f else 1f) // mirror that icon
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer { //alows to do any kind of 3d anim
                    transformOrigin = TransformOrigin(0.5f, 0f)
                    rotationX = rotatex.value
                }
                .alpha(alpha.value)
        ){
            content()
        }
    }
}