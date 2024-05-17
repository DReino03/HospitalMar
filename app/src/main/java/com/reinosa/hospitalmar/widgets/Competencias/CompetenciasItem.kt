package com.reinosa.hospitalmar.widgets.Competencias


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DataClass.Competencia
import com.reinosa.hospitalmar.ViewModel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun CompetenciasItem(text: String, navController: NavController, viewModel: ViewModel, competencia: Competencia) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            //.border(2.dp, borderColor.value)
            .clickable {
                viewModel.setSelectedCompetencia(competencia)
                if (viewModel.comeFromInforme){
                    if (viewModel.isAlumno){
                        CoroutineScope(Dispatchers.Main).launch {
                            val job = async(Dispatchers.IO) {
                                viewModel.getInforme(viewModel.currentAlumno.value!!.idAlumno ,viewModel.moduloSelected!!.idModulo, viewModel.competenciaSelected!!.idCompetencia)
                            }
                            job.await()
                            navController.navigate("informeFuera")
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
                            navController.navigate("informeFuera")
                        }
                    }

                }else{
                    navController.navigate("evaluate")
                }
            },
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = text,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            )
            Icon(
                Icons.Filled.ArrowForwardIos,
                contentDescription ="Continue",
                modifier = Modifier
                    .weight(0.2f) // Asigna un peso al icono
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
    }


}


