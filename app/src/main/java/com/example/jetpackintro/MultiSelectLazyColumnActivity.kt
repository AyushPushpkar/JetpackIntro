package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackintro.ui.theme.JetPackIntroTheme

class MultiSelectLazyColumnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackIntroTheme {
                var item by remember {
                    mutableStateOf(
                        (1..20).map {
                            ListItem(
                                title = "Item $it",
                                isChecked = false
                            )
                        }
                    )

                }

                // item.filter { it.isChecked }

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                        .background(Color.Black)
                ) {
                    items(item.size){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    item = item.mapIndexed { index, Item ->
                                        if(it == index){
                                            Item.copy(isChecked = !Item.isChecked)
                                        }else Item
                                    }
                                }
                                .padding(16.dp) ,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = item[it].title , color = Color.White)
                            if(item[it].isChecked){
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "selcted" ,
                                    tint = Color.Green ,
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    private fun enableEdgeToEdge(){}
}