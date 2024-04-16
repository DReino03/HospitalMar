package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ModulScreen(navController: NavController){

    LazyColumn {
        item { Text("Moduls") }
        items(10) {
            ModulItem("Modul $it", navController)
        }
    }

}