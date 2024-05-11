package com.reinosa.hospitalmar.widgets.Evaluacio

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.widgets.StudentList.StudentItem


@Composable
fun ModulScreen(navController: NavController, viewModel: LoginViewModel) {

    Log.d("PROFESOR ACTUAL", viewModel.currentProfesor.value.toString())
    Log.d("ALUMNO ACTUAL", viewModel.currentAlumno.value.toString())

    viewModel.getModulos()
    val modulList = viewModel.modulList.value

    Log.d("Modulos", viewModel.modulList.value.toString())

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                "Módulos",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        modulList?.let { list ->
            items(list.size) { index ->
                val modul = list[index]
                ModulItem(text = modul.nombreModul, navController = navController)
            }
        }
    }
}

