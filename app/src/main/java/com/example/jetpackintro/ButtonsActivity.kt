package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ButtonsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //state -> a value that can change
            //represent some part of ui
            val snackbarHostState = remember { SnackbarHostState() }
            var textFieldState by remember {
                mutableStateOf("")            // by default no state
            }
            Scaffold(                        //provide a layout -> used for snackbar , nav drawer , toolbar ,etc (material component)
                modifier = Modifier.fillMaxSize(),
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState
                    )
                }
            ) { paddingValues ->
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(30.dp) ,
                    horizontalAlignment = Alignment.CenterHorizontally ,
                    verticalArrangement = Arrangement.Center
                ){
                    TextField(value = textFieldState,                 //link textfield to a state
                        label = { Text(text = "Enter your name") },
                        onValueChange = { textFieldState = it } ,   // func gives string it with which update the textfieldstate
                        singleLine = true ,
                        modifier = Modifier.fillMaxWidth()
                    ) 

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        lifecycleScope.launch {
                            snackbarHostState.showSnackbar("Hello $textFieldState")
                        }
                    }) {
                        Text(text = "Pls greet Me")
                    }
                }
            }
        }
    }

    private fun enableEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}
