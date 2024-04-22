package com.reinosa.hospitalmar.widgets.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ui.theme.blueproject

@Composable
fun HomeButton(
    navigation: NavController,
    text: String,
    imageRoute: Int,
    destination: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        IconButton(
            onClick = {
                navigation.navigate(destination)
            },
            modifier = Modifier
                .size(90.dp)
                .background(color = blueproject, shape = RoundedCornerShape(12.dp))
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = imageRoute),
                contentDescription = "Graphics",
                modifier = Modifier.size(60.dp)
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )
    }
}



