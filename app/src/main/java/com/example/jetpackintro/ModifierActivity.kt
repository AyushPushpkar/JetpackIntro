package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackintro.ui.theme.Jade
import com.example.jetpackintro.ui.theme.Pink
import kotlin.random.Random

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column (modifier = Modifier.fillMaxSize()){
                Column(
                    modifier = Modifier
                        .background(Pink)
                        .fillMaxHeight(0.6f)
                        .fillMaxWidth()
//                    .requiredWidth(600.dp)
                        .padding(10.dp)
                        .border(3.dp, Color.Cyan, shape = RoundedCornerShape(15.dp))
                        .padding(top = 32.dp, start = 64.dp)
                ) {
                    Text(text = "JetPack")
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Compose")
                    Text("Modifiers" ,
                        modifier = Modifier
                            .offset(y = 20.dp) //won't push other elements  like margin
                            .clickable { }
                            .background(Jade)
                            .border(2.dp, Color.Cyan)
                            .padding(10.dp)
                    )
                }
                val color = remember {
                    mutableStateOf(Color.Cyan)
                }
                Colorbox2(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ){
                    color.value = it
                }
                Box(
                    modifier = Modifier
                        .background(color.value)
                        .weight(1f)
                        .fillMaxSize()
                ) {}
            }
        }
    }

    private fun enableEdgeToEdge() {}
}

@Composable
fun Colorbox2(
    modifier: Modifier = Modifier ,
    updateColor: (Color) -> Unit
){

    Box (
        modifier = modifier
            .background(Color.DarkGray)
            .clickable {
                updateColor(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f   // opacity
                    )
                )
            }
    ){
    }
}

@Composable
fun Colorbox(modifier: Modifier = Modifier){      // recomposed to show state change
    val color = remember { mutableStateOf(Color.Cyan) }  // won't reset after recomposition

    Box (
        modifier = modifier
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f   // opacity
                )
            }
    ){

    }
}
