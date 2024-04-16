package com.reinosa.hospitalmar.widgets.Evaluacio

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R


@Composable
fun ModulItem(text:String ,navController: NavController) {
Card(
        modifier = Modifier
            .background(Color.Magenta)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Row() {
            Text(
                text = text,
                Modifier.padding(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription ="BACK",
                modifier = Modifier
                .size(60.dp)
                .clickable { navController.navigate("evaluate")
                Log.e("ModulItem", "click")}
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))

    }
}