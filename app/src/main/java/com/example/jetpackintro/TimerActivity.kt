package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class TimerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                color = Color.Black ,
                modifier = Modifier.fillMaxSize()
            ) {
                Box (
                    contentAlignment = Alignment.Center
                ){
                    Timer(
                        totalTime = 100L * 1000L,
                        handleColor = Color.Green,
                        inactiveBarColor = Color.DarkGray ,
                        activeBarColor = Color(0xFF378900) ,
                        modifier = Modifier.size(200.dp) ,
                    )
                }
            }
        }
    }
}

@Composable
fun Timer(
    totalTime : Long ,
    handleColor : Color ,
    inactiveBarColor : Color ,
    activeBarColor : Color ,
    modifier: Modifier = Modifier ,
    initialValue : Float = 1f ,
    strokeWidth : Dp = 10.dp
){
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableFloatStateOf(initialValue)
    }
    var currentTime by remember {
        mutableLongStateOf(totalTime)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = currentTime , key2 = isTimerRunning) {
        if(currentTime >= 0 && isTimerRunning){
            delay(100L)
            currentTime -= 100L
            value = currentTime/totalTime.toFloat()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ){
       Canvas(modifier = modifier){
           drawArc(
               color = inactiveBarColor ,
               startAngle = -215f ,
               sweepAngle = 250f ,
               useCenter = false ,
               size = Size(size.width.toFloat() , size.height.toFloat()) ,
               style = Stroke(strokeWidth.toPx() , cap =  StrokeCap.Round)
           )
           drawArc(
               color = activeBarColor ,
               startAngle = -215f ,
               sweepAngle = 250f * value ,
               useCenter = false ,
               size = Size(size.width.toFloat() , size.height.toFloat()) ,
               style = Stroke(strokeWidth.toPx() , cap =  StrokeCap.Round)
           )

           val center = Offset(size.width/2f , size.height/2f)  // co-ordinate in a 2D space
           var radius = size.width/2f
           rotate(250*value - 215f , pivot = center){
               drawPoints(
                   listOf(Offset(center.x +radius, center.y)) ,
                   pointMode = PointMode.Points ,
                   color = handleColor ,
                   strokeWidth = (strokeWidth*3f).toPx() ,
                   cap = StrokeCap.Round
               )
           }
       }
        Text(
            text = (currentTime/1000L).toString() ,
            fontSize = 44.sp ,
            fontWeight = FontWeight.Bold ,
            color = Color.White
        )
        Button(
            onClick = {
                if(currentTime <= 0L){
                    currentTime = totalTime
                    isTimerRunning = true
                }else{
                    isTimerRunning = !isTimerRunning
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter) ,
            colors = ButtonDefaults.buttonColors(
                containerColor = if(!isTimerRunning || currentTime <= 0L) Color.Green else Color.Red
            )
        ) {
            Text(
                text = if (isTimerRunning && currentTime >= 0L) "Stop"
                else if(!isTimerRunning && currentTime >= 0L) "start"
                else "Restart"
            )
        }
    }
}