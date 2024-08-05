package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackintro.ui.theme.JetPackIntroTheme

class ScreenSizesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackIntroTheme {
                val windowInfo = rememberWindowInfo()

                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
                    //list1
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(10) {
                            Text(
                                text = "items $it",
                                fontSize = 25.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Cyan)
                                    .padding(16.dp)
                            )
                        }
                        //list2
                        items(10) {
                            Text(
                                text = "items $it",
                                fontSize = 25.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                                    .padding(16.dp)
                            )
                        }
                    }
                }
                else{
                    Row(modifier = Modifier.fillMaxWidth()) {
                        //list1
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(10) {
                                Text(
                                    text = "items $it",
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Cyan)
                                        .padding(16.dp)
                                )
                            }
                        }
                        //list2
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(10) {
                                Text(
                                    text = "items $it",
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Green)
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}