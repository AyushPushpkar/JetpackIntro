package com.example.jetpackintro

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name : String ,
    val route : String ,
    val icon : ImageVector ,
    val badge : Int = 0
)
