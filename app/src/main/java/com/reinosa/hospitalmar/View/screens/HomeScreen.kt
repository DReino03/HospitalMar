package com.reinosa.hospitalmar.View.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DrawerParams
import com.reinosa.hospitalmar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    //val ColorsList = listOf()
    val screens = listOf("Home", "Profile", "Settings", "About", "Contact", "Help", "Logout")
    LazyColumn(content = {
        item {
            TopAppBar(
                title = { Text(text = "Bienvenido Persona") },
            )
        }
        items(screens.size) { index ->

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(100.dp)
                            .weight(1f)
                            .background(Color.Blue)
                        
                    ) {
                        Text(
                            text = screens[index],

                            style = MaterialTheme.typography.h6,

                            textAlign = TextAlign.Center,

                            modifier = Modifier

                                .fillMaxWidth()
                                .background(Color.Blue),


                        )

                    }
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(100.dp)
                            .weight(1f)
                            .background(Color.Blue)
       
                    ) {
                        Text(
                            text = screens[index],
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Blue)

                        )

                    }

                }

        }
    })
}







