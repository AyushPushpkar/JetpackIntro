package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackintro.ui.theme.Pink

class RowColumnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val fontFamily= FontFamily(
            Font(R.font.oswald_light , FontWeight.Light) ,
            Font(R.font.oswald_extralight , FontWeight.ExtraLight) ,
            Font(R.font.oswald_bold , FontWeight.Bold) ,
            Font(R.font.oswald_regular , FontWeight.Normal) ,
            Font(R.font.oswald_medium , FontWeight.Medium) ,
            Font(R.font.oswald_semibold, FontWeight.SemiBold)
        )

        setContent {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Pink),
                horizontalAlignment = Alignment.CenterHorizontally, // cross axis of column
                verticalArrangement = Arrangement.SpaceEvenly   // main axis of column
            ) {

                Row(
                    modifier = Modifier
                        .background(Color.Cyan)
                        .width(200.dp)
                        .height(100.dp),
                    horizontalArrangement = Arrangement.SpaceAround,  // main axis of row
                    verticalAlignment = Alignment.CenterVertically  // cross axis of row
                ) {
                    Text(text = "JetPack")
                    Text(text = "Compose")
                }
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.DarkGray,
                                fontSize = 25.sp
                            )
                        ){
                            append("Great ")
                        }
                        append("Power\n")
                        append("Great ")
                        withStyle(
                            style = SpanStyle(
                                color = Color.DarkGray,
                                fontSize = 25.sp
                            )
                        ){
                            append("Responsibility")
                        }
                    } ,
                    color = Color.White,
                    fontSize = 25.sp,
                    fontFamily = fontFamily ,
                    fontWeight = FontWeight.Bold ,
                    fontStyle = FontStyle.Normal ,
                    textAlign = TextAlign.Center ,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }

    private fun enableEdgeToEdge() {}
}

