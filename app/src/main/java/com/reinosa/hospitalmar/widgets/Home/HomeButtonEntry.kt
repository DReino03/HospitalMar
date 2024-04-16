package com.reinosa.hospitalmar.widgets.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R


@Composable
fun HomeButton(
               navigation: NavController,
               text: String,
               imageRoute: Int,
               destination: String){
    Column {
        Spacer(modifier = Modifier.padding(10.dp))
        IconButton(onClick = {
            navigation.navigate(destination)
        }, modifier = Modifier
            .background(color = Color(105, 114, 204))
            .size(100.dp)
            .border(1.dp, Color.Black)){
            Icon(painter = painterResource(id = imageRoute), contentDescription = "Grafics", Modifier.size(70.dp))

            Spacer(modifier = Modifier.padding(30.dp))

        }
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = text, textAlign = TextAlign.Justify, fontWeight = FontWeight.Medium)
    }
}


