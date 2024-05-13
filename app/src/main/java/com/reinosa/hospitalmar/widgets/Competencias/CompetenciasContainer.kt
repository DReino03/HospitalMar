package com.reinosa.hospitalmar.widgets.Competencias

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.LoginViewModel

@Composable
fun CompetenciasContainer(navController: NavController, viewModel: LoginViewModel) {
    var loading by remember { mutableStateOf(true) } // Estado de carga inicial

    LaunchedEffect(viewModel.alumnosPorIdProfesor) {

        // Observa cambios en alumnosPorIdProfesor y actualiza el estado de carga
        loading = viewModel.alumnosPorIdProfesor.value == null
    }

    if (loading) {
        // Muestra un CircularProgressIndicator mientras se carga
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        // Mostrar la lista de alumnos cuando los datos están disponibles
        val alumnosList = viewModel.alumnosPorIdProfesor.value ?: emptyList()

        LazyColumn {
            item {
                Spacer(modifier = Modifier.padding(12.dp))
                Text(
                    "Competencias",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(alumnosList) { alumno ->
                CompetenciasItem(navController, viewModel)
            }
        }
    }
}
