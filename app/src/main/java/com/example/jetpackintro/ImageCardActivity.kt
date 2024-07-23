package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackintro.ui.theme.JetPackIntroTheme
import com.example.jetpackintro.ui.theme.Pink

class ImageCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val painter = painterResource(id = R.drawable.coder)
            val des = "Coder"
            val title = "JetPack Compose"
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Pink)
                    .padding(15.dp)
            ){
                ImageCard(painter = painter, contentDes = des, title = title)
            }

        }
    }
    private fun enableEdgeToEdge(){}
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDes : String,
    title : String,
    modifier: Modifier = Modifier
){
    Card(
        modifier = Modifier.fillMaxWidth() ,
        shape = RoundedCornerShape(17.dp) ,
        elevation = CardDefaults.cardElevation(5.dp)
    ){
        Box (modifier = Modifier.height(200.dp)){
            Image(painter = painter , contentDescription = contentDes , contentScale = ContentScale.Crop)

            Box (
                modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent ,
                            Color.Black
                        ) ,
                        startY = 200f
                    )
                )
            ){

            }

            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(title , style = TextStyle(color = Color.White , fontSize = 16.sp))
            }
        }
    }
}
