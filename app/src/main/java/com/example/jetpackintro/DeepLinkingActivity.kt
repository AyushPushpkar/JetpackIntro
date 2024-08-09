package com.example.jetpackintro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.jetpackintro.ui.theme.JetPackIntroTheme

class DeepLinkingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackIntroTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home" ){
                    composable("home") {
                        Box (
                            modifier = Modifier.fillMaxSize() ,
                            contentAlignment = Alignment.Center
                        ){
                             Button(onClick = {
                                 navController.navigate("detail")
                             }) {
                                 Text(text = "To Detail")
                             }
                        }
                    }
                    composable(
                        "detail" ,
                        deepLinks = listOf(
                            navDeepLink {
                                uriPattern = "https://ap-coding.com/{id}"
                                action = Intent.ACTION_VIEW
                            }
                        ) ,
                        arguments = listOf(
                            navArgument("id"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        val id = it.arguments?.getInt("id")
                        Box (
                            modifier = Modifier.fillMaxSize() ,
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = "The id is $id")
                        }
                    }
                }
            }
        }
    }
}

