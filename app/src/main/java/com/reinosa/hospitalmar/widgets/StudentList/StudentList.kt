package com.reinosa.hospitalmar.widgets.StudentList

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
import com.reinosa.hospitalmar.ViewModel.ViewModel

@Composable

fun StudentList(navController: NavController, viewModel: ViewModel) {
    Log.d("Come from Infrome", viewModel.comeFromInforme.toString())
//    viewModel.getAlumnosIdProfesor()
    val alummnoList = viewModel.alumnosPorIdProfesor.value

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                "Alumnes",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        alummnoList?.let { list ->
            items(list.size) { index ->
                val alumno = list[index]
                StudentItem(text = alumno.nombre, navController = navController, viewModel, alumno)
            }
        }
    }
}



