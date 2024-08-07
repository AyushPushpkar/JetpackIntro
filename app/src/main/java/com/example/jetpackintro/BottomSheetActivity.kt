package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.jetpackintro.ui.theme.JetPackIntroTheme
import kotlinx.coroutines.launch

class BottomSheetActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackIntroTheme {
                val sheetState = rememberStandardBottomSheetState(
                    initialValue = SheetValue.PartiallyExpanded ,
                )
                val scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = sheetState
                )
                val scope = rememberCoroutineScope()

                BottomSheetScaffold(
                    scaffoldState = scaffoldState ,
                    sheetContent = {
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp) ,
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = "Bottom Sheet" , fontSize = 48.sp)
                        }
                    } ,
                    sheetContainerColor = Color.Green
                ) {
                    Box (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it) ,
                        contentAlignment = Alignment.Center
                    ){
                        Button(
                            shape = ButtonDefaults.elevatedShape,
                            colors = ButtonDefaults.buttonColors(Color.Green),
                            onClick = {
                                scope.launch {
                                    if(sheetState.currentValue == SheetValue.PartiallyExpanded){
                                        sheetState.expand()
                                    }
                                    else{
                                        sheetState.partialExpand()
                                    }
                                }
                            }
                        ) {
                            Text(text = "Bottom sheet fraction : ${sheetState.currentValue}")
                        }
                    }
                }
            }
        }
        window.statusBarColor = Color.Green.hashCode()
    }

    private fun enableEdgeToEdge(){}
}
