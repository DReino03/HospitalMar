package com.reinosa.hospitalmar.widgets.DropDownMenu

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
@Composable
fun DropdownMenuWidget(viewModel: ViewModel, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Selecciona una Competencia") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = selectedOption,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(16.dp)
                .background(Color.White)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            viewModel.competenciaList.value?.forEach { option ->
                DropdownMenuItem(onClick = {
                    selectedOption = option.nombreCompetencia
                    viewModel.competenciaSelectedText.value = option.nombreCompetencia
                    expanded = false
                    viewModel.setSelectedCompetencia(option)
                    if (viewModel.comeFromInforme){
                        if (viewModel.isAlumno){
                            CoroutineScope(Dispatchers.Main).launch {
                                val job = async(Dispatchers.IO) {
                                    viewModel.getInforme(viewModel.currentAlumno.value!!.idAlumno ,viewModel.moduloSelected!!.idModulo, viewModel.competenciaSelected!!.idCompetencia)
                                }
                                job.await()
                            }
                        } else{
                            CoroutineScope(Dispatchers.Main).launch {
                                val job = async(Dispatchers.IO) {
                                    Log.d("Current alumno informe", viewModel.alumnoSelected!!.idAlumno.toString())
                                    Log.d("Current modulo informe", viewModel.moduloSelected!!.idModulo.toString())
                                    Log.d("Current competencia informe", viewModel.competenciaSelected!!.idCompetencia.toString())
                                    viewModel.getInforme(viewModel.alumnoSelected!!.idAlumno ,viewModel.moduloSelected!!.idModulo, viewModel.competenciaSelected!!.idCompetencia)
                                }
                                job.await()
                                navController.navigate("evaluate")
                            }
                        }
                }}) {
                    Text(text = option.nombreCompetencia)
                }
            }
        }
    }
}