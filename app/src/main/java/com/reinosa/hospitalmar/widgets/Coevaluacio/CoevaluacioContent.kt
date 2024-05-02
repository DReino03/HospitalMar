package com.reinosa.hospitalmar.widgets.Coevaluacio

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.HmViewmodel

@Composable

fun CoevaluacioContent(navController: NavController, coevalViewModel: HmViewmodel) {

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text("Alumnes", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp)) }
        items(10) {
            StudentItem(text = "Persona", navController = navController, coevalViewModel = HmViewmodel())
        }
    }


}
