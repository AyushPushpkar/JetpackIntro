package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackintro.ui.theme.JetPackIntroTheme

class PaginationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackIntroTheme {
                val viewModel = viewModel<PageViewModel>()
                val state = viewModel.state
                LazyColumn(
                     modifier = Modifier.fillMaxSize()
                ) {
                    items(state.items.size){
                        val item = state.items[it]
                        if (it >= state.items.size - 1 && !state.endReached && !state.isLoading){
                            viewModel.loadNextItems()
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = item.title , fontSize = 20.sp , color = Color.Black)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = item.description)
                        }
                    }
                    item {
                        if (state.isLoading){
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp) ,
                                horizontalArrangement = Arrangement.Center
                            ){
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
    private fun enableEdgeToEdge(){}
}