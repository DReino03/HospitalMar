package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ModulScreen(navController: NavController){

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text("Móduls", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp)) }
        items(10) {
            ModulItem("Módul $it", navController)
        }
    }


}