package com.reinosa.hospitalmar.widgets.EvalResult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ResultContent(navController: NavController) {
    val persona = "Persona"
    val modulo = "Modulo"
    val rating = listOf(1, 2, 3, 4, 5)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Persona: $persona", style = MaterialTheme.typography.h5)
        Text(text = "Modulo: ${modulo ?: "No proporcionado"}", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Calificaciones:", style = MaterialTheme.typography.h5)
        LazyColumn {
            items(rating.size) { item ->
                Text(text = "Calificación: ${item} - ${item}", style = MaterialTheme.typography.body1)
            }
        }
    }
}