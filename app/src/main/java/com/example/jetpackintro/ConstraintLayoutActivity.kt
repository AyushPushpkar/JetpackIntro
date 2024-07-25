package com.example.jetpackintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.jetpackintro.ui.theme.JetPackIntroTheme

class ConstraintLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val constraints = ConstraintSet {
                val greenBox = createRefFor("greenbox")
                val redBox = createRefFor("redbox")
                val guideline = createGuidelineFromTop(0.2f)

                constrain(greenBox){
                    top.linkTo(guideline)
                    start.linkTo(parent.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                constrain(redBox){
                    top.linkTo(guideline)
                    start.linkTo(greenBox.end)
                    end.linkTo(parent.end)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                createHorizontalChain(greenBox,redBox , chainStyle = ChainStyle.Spread)
            }

            ConstraintLayout(constraints , modifier = Modifier.fillMaxSize()) {

                Box (modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenbox"))
                Box (modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redbox"))
            }
        }
    }

    private fun enableEdgeToEdge(){}
}