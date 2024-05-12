package com.reinosa.hospitalmar.widgets.Informe

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
import com.reinosa.hospitalmar.ViewModel.LoginViewModel

@Composable
fun InformeContent(navController: NavController, loginViewModel: LoginViewModel) {
    val informeDataList = loginViewModel.informeDataList.value

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "${loginViewModel.studentsSelected}", style = MaterialTheme.typography.h5)
        Text(text = "Modulo: ${loginViewModel.modulList}", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Calificaciones:", style = MaterialTheme.typography.h5)
        LazyColumn {
            items(informeDataList?.size ?: 0) { index ->
                val informeData = informeDataList?.get(index)
                informeData?.let {
                    Text(text = "Modulo: ${it.modul}", style = MaterialTheme.typography.body1)
                    it.persona.value.forEach { persona ->
                        Text(text = "Persona: $persona", style = MaterialTheme.typography.body1)
                    }
                    it.rating.forEach { rating ->
                        Text(text = "Rating: ${rating.first} - ${rating.second}", style = MaterialTheme.typography.body1)
                    }
                    it.observaciones.forEach { observacion ->
                        Text(text = "Observacion: $observacion", style = MaterialTheme.typography.body1)
                    }
                }
            }
        }
    }
}