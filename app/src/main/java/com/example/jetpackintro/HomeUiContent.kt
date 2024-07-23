package com.example.jetpackintro

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackintro.ui.theme.Jade

@Composable
fun homeUI() {
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){

        Column (modifier = Modifier.fillMaxSize()) {
            Row( modifier = Modifier
                .background(Jade)
                .fillMaxWidth()
                .height(56.dp)
            ) {
                buttons(icon = R.drawable.baseline_menu_24)
                Column ( modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center ){
                    Text(text = "JetPack"  ,
                        style = TextStyle(fontSize =  20.sp , color = Color.White , fontWeight = FontWeight.W700 )
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    buttons(icon = R.drawable.round_youtube_searched_for_24)
                }

            }
            Card(modifier = Modifier.fillMaxWidth()) {
                LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                    items(10) {
                        Text(text = "Item $it")
                    }
                })
            }

        }
    }

}


@Composable
fun buttons(
    @DrawableRes icon: Int,
    tint: Color = Color.Unspecified,
) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(painter = painterResource(id = icon), contentDescription = "" )
    }


}