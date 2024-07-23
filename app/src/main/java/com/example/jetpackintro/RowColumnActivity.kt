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
import androidx.compose.ui.unit.dp
import com.example.jetpackintro.ui.theme.Pink

class RowColumnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                Text(text = "Beginner")
            }
        }
    }

    private fun enableEdgeToEdge() {}
}

