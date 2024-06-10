package com.reinosa.hospitalmar.widgets.Evaluacio

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.ViewModel
import com.reinosa.hospitalmar.widgets.DropDownMenu.DropdownMenuWidget
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EvaluarScreen(navController: NavController, viewModel: ViewModel) {
    val ratings: MutableList<Pair<String, Int>> = mutableListOf()
    val comments = remember { mutableStateListOf<MutableList<String>>() }

    repeat(5) {
        comments.add(mutableListOf())
    }

    Column {
        LazyColumn {

/*
            item {
                Spacer(modifier = Modifier.padding(12.dp))
                val competenciaSelected = viewModel.competenciaSelected
                LaunchedEffect(key1 = competenciaSelected) {}
                    Text(
                        text = competenciaSelected?.nombreCompetencia ?: "Seleccionar competencia",
                        style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = competenciaSelected?.descripcion ?: "",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                DropdownMenuWidget(viewModel = viewModel, navController)

            }
 */

            viewModel.competenciaSelected?.let { competencia ->
                items(4) { index ->
                    val pregunta = when (index) {
                        0 -> competencia.pregunta1
                        1 -> competencia.pregunta2
                        2 -> competencia.pregunta3
                        3 -> competencia.pregunta4
                        else -> ""
                    }
                    viewModel.listComentarios = comments
                    EvalItem(text = pregunta, index, comments, viewModel)
                }
            }

            item {
                // Añade las calificaciones a la lista
                viewModel.competenciaSelected?.descripcion?.let { descripcion ->
                    repeat(5) {
                        val data = Pair(descripcion, 0)
                        ratings.add(data)
                    }
                }
            }
        }
    }
}