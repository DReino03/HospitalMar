package com.reinosa.hospitalmar.widgets.Informe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.ViewModel
import com.reinosa.hospitalmar.widgets.Evaluacio.EvalItem
import com.reinosa.hospitalmar.widgets.Modulos.ModulItem


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InformeScreen(navController: NavController, viewModel: ViewModel) {
    val notasList = viewModel.notasList.value

    Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier
                .height(16.dp)
            )
            Text(
                text = "Qualificacions:",
                style = MaterialTheme.typography.h5
            )
            LazyColumn {
                notasList?.let { list ->
                    items(list.size) { index ->
                        val nota = list[index]
                        val competencia = viewModel.competenciaSelected.let { competencia ->
                            when (index) {
                                0 -> competencia?.pregunta1
                                1 -> competencia?.pregunta2
                                2 -> competencia?.pregunta3
                                3 -> competencia?.pregunta4
                                else -> ""
                            }
                        }
                        CardInfo(nota.comentario, viewModel, nota.nota, competencia.toString())
                    }
                }

            }
        }
    }
