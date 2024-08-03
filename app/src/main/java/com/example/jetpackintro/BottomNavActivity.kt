package com.example.jetpackintro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackintro.ui.theme.JetPackIntroTheme

class BottomNavActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackIntroTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavBar(
                            item = listOf(
                                BottomNavItem(
                                    name = "Home" ,
                                    route = "home" ,
                                    icon = Icons.Default.Home
                                ) ,
                                BottomNavItem(
                                    name = "Settings" ,
                                    route = "setting" ,
                                    icon = Icons.Default.Settings
                                ) ,
                                BottomNavItem(
                                    name = "Chat" ,
                                    route = "chat" ,
                                    icon = Icons.Default.Notifications ,
                                    badge = 12
                                )
                            ),
                            navController = navController ,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Navigation3(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation3(navController: NavHostController){

    NavHost(navController = navController, startDestination = "home" ){
        composable("home"){
            HomeScreen()
        }
        composable("chat"){
            ChatScreen()
        }
        composable("setting"){
            SettingScreen()
        }
    }
}

@Composable
fun BottomNavBar(
    item : List<BottomNavItem> ,
    navController: NavController ,
    modifier: Modifier = Modifier ,
    onItemClick : (BottomNavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier ,
        containerColor = Color.Black ,
        tonalElevation = 8.dp
    ) {
        item.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected =  selected,
                onClick = { onItemClick(it) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Green,
                    unselectedIconColor = Color.Gray ,
                    selectedTextColor = Color.Green ,
                    unselectedTextColor = Color.Gray ,
                ),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.SpaceAround ) {
                        if(it.badge > 0){
                            BadgedBox(
                                badge = {
                                    Badge(
                                        containerColor = Color.Red,  // Badge background color
                                        contentColor = Color.White ,   // Badge text color
                                        modifier = Modifier
                                                .offset(x = 8.dp, y = (-8).dp) // Position badge to top-right corner
                                    ) {
                                        Text(
                                            text =  it.badge.toString() ,
                                            color = Color.White ,
                                        )
                                    }
                                }
                            ) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = it.name ,
                                )
                            }
                        }
                        else{
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.name
                            )
                        }
                        if(selected){
                            Text(
                                text = it.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                },
            )
        }
    }
}

@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Home Screen")
    }
}

@Composable
fun SettingScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Setting Screen")
    }
}

@Composable
fun ChatScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Chat Screen")
    }
}