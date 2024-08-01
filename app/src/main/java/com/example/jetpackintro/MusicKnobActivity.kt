package com.example.jetpackintro

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackintro.ui.theme.Pink
import kotlin.math.atan2
import kotlin.math.roundToInt

class MusicKnobActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ){
                Row (
                    horizontalArrangement = Arrangement.Center ,
                    verticalAlignment = Alignment.CenterVertically ,
                    modifier = Modifier
                        .border(1.dp, color = Color.Green, RoundedCornerShape(10.dp))
                        .padding(30.dp)
                ){
                    var volume by remember {
                        mutableFloatStateOf(0f)
                    }
                    var barCount = 20
                    MusicKnob(
                        modifier = Modifier.size(100.dp)
                    ) {
                        volume = it    // % value
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                    VolumeBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp) ,
                        activeBars = (barCount * volume).roundToInt(),
                        barCount = barCount
                    )
                }
            }
        }

        window.statusBarColor = android.graphics.Color.GREEN
    }

    private fun enableEdgeToEdge() {}
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitingAngle : Float = 50f,
    onValueChange : (Float) -> Unit  // the % value the knob is currently rotated to
){
    var rotation by remember {             // current rotation of music knob
        mutableFloatStateOf(limitingAngle)
    }
    var touchX by remember {
        mutableFloatStateOf(0f)
    }
    var touchY by remember {
        mutableFloatStateOf(0f)
    }
    var centerX by remember {
        mutableFloatStateOf(0f)
    }
    var centerY by remember {
        mutableFloatStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "music kob" ,
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {    // where center of image is
                var windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter {  // to detect position of text
                touchX = it.x
                touchY = it.y

                val angle = atan2(centerY - touchY, centerX - touchX) * (180f / Math.PI).toFloat()

                when (it.action) {
                    MotionEvent.ACTION_DOWN,  //when we started the tap
                    MotionEvent.ACTION_MOVE
                    -> {
                        if (angle !in -limitingAngle..limitingAngle) {
                            val fixedAngle = if (angle in -180f..-limitingAngle) {
                                360f + angle
                            } else {
                                angle
                            }

                            rotation = fixedAngle

                            val percent =
                                (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)  // (angle -min)/max
                            onValueChange(percent)
                            true
                        } else false
                    }

                    else -> false
                }
            }
            .rotate(rotation)
    )

}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0 ,
    barCount : Int = 10
){
    BoxWithConstraints(
        contentAlignment = Alignment.Center ,
        modifier = modifier
    ) {
        var barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }
        Canvas(modifier = modifier) {
            for(i in 0 until barCount){
                drawRoundRect(
                    color = if(i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }

    }
}