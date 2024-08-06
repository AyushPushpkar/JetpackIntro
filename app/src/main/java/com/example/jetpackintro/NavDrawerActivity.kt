package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.jetpackintro.ui.theme.JetPackIntroTheme
import kotlinx.coroutines.launch

class NavDrawerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackIntroTheme {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        NavDrawerContent(
                            onItemClick = {
                                // Handle navigation or state update
                                println("Clicked item: ${it.title}")
                                scope.launch { drawerState.close() }
                            }
                        )
                    } ,
                    gesturesEnabled = drawerState.isOpen
                ) {
                    Scaffold(
                        topBar = {
                            AppBar(
                                onNavigationIconClick = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) { innerPadding ->
                        // Main content goes here
                        Box(modifier = Modifier.padding(innerPadding)) {
                            // Your main content goes here
                        }
                    }
                }
            }
        }
        window.statusBarColor = Color(0xFFDD5207).hashCode()
    }

    private fun enableEdgeToEdge() {
        // Your implementation here
    }
}

@Composable
fun NavDrawerContent(
    onItemClick: (NavDrawerItem) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.8f)
            .fillMaxHeight()
    ) {
        DrawerHeader()

        // Add a divider between the header and body
        Divider(
            color = Color.Gray, // Set the color of the divider
            thickness = 1.dp // Set the thickness of the divider
        )

        // Custom divider
        Box(
            modifier = Modifier
                .height(1.dp) // Divider thickness
                .background(Color.Gray) // Divider color
                .fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(50.dp))

        DrawerBody(
            item = listOf(
                NavDrawerItem(
                    id = "home",
                    icon = Icons.Default.Home,
                    title = "Home",
                    contentDes = "Go to Home"
                ),
                NavDrawerItem(
                    id = "setting",
                    icon = Icons.Default.Settings,
                    title = "Settings",
                    contentDes = "Go to Settings"
                ),
                NavDrawerItem(
                    id = "help",
                    icon = Icons.Default.Info,
                    title = "Help",
                    contentDes = "Get Help"
                )
            ),
            onItemClick = onItemClick
        )
    }
}

