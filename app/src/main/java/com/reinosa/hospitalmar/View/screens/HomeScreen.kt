package com.reinosa.hospitalmar.View.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    //val ColorsList = listOf()
    val screens = listOf("Home", "Profile", "Settings", "About", "Contact", "Help", "Logout" , "Login")
    LazyColumn(content = {
        item {
            TopAppBar(
                title = { Text("Bienvenido") },
            )
        }
        items(screens.size){
            index ->  Card(
            modifier = Modifier
                .background(Color.Magenta)
                .padding(16.dp))
        {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = screens[index],
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp)
                    )
                    Icon(painterResource(id = R.drawable.ic_star), contentDescription ="Star")
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }

        }



    })
}