package com.reinosa.hospitalmar.widgets.Drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ui.theme.blueproject

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .height(180.dp)
            .background(blueproject)
            .padding(16.dp)
        ,
        contentAlignment = Alignment.BottomStart,

        ){
        Column {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "username",
                style = MaterialTheme.typography.h5,
                color = Color.White,
            )
            Text(text = "ibaux00xxx", style = MaterialTheme.typography.h6)
        }
    }
}
