package com.reinosa.hospitalmar.widgets.Coevaluacio

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.widgets.Evaluacio.ModulItem

@Composable

fun StudentList(navController: NavController, viewModel: LoginViewModel) {

    Log.d("PROFESOR ACTUAL", viewModel.currentProfesor.value.toString())
    Log.d("ALUMNO ACTUAL", viewModel.currentAlumno.value.toString())

    viewModel.getAlumnosIdProfesor()
    val alummnoList = viewModel.alumnosPorIdProfesor.value

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                "Alumnos",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        alummnoList?.let { list ->
            items(list.size) { index ->
                val alumno = list[index]
                ModulItem(text = alumno.nombre, navController = navController)
            }
        }
    }
}


