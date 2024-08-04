package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.jetpackintro.ui.theme.JetPackIntroTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale

class PermissionActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackIntroTheme {
                val permissionsState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA
                    )
                )

                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(
                    key1 = lifecycleOwner,
                    effect = {
                        val observer = LifecycleEventObserver{ _ , event ->
                            if(event == Lifecycle.Event.ON_START){
                                permissionsState.launchMultiplePermissionRequest()
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)

                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    }
                )
                Column(
                    modifier = Modifier.fillMaxSize().padding(15.dp) ,
                    horizontalAlignment = Alignment.CenterHorizontally ,
                    verticalArrangement = Arrangement.Center ,
                ) {
                    permissionsState.permissions.forEach {
                        when(it.permission) {
                            Manifest.permission.CAMERA -> {
                                when{
                                    it.status.isGranted -> {
                                        Text(text = "Camera Permission accepted")
                                    }
                                    it.status.shouldShowRationale ->{
                                        Text(text = "Camera Permission is needed")
                                    }
                                    it.isPermanentlyDenied() -> {
                                        Text(text = "Camera Permission was necessary , enable it in app settings")
                                    }
                                }
                            }
                            Manifest.permission.RECORD_AUDIO -> {
                                when{
                                    it.status.isGranted -> {
                                        Text(text = "Record Audio Permission accepted")
                                    }
                                    it.status.shouldShowRationale ->{
                                        Text(text = "Record Audio Permission is needed")
                                    }
                                    it.isPermanentlyDenied() -> {
                                        Text(text = "Record Audio Permission was necessary , enable it in app settings")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
