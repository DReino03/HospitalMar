package com.reinosa.hospitalmar.widgets.Modulos

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
import com.reinosa.hospitalmar.Model.DataClass.Modulo
import com.reinosa.hospitalmar.ViewModel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@Composable
fun ModulItem(text:String, navController: NavController, viewModel: ViewModel, modulo: Modulo) {
Card(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .clickable {
                if (viewModel.comeFromInforme) {
                    viewModel.setSelectedModulo(modulo)
                    CoroutineScope(Dispatchers.Main).launch {
                        val job = async(Dispatchers.IO) {
                            viewModel.selectAllCompetencias()
                        }
                        job.await()
                        navController.navigate("competencias")
                    }

                } else {
                    viewModel.setSelectedModulo(modulo)
                    CoroutineScope(Dispatchers.Main).launch {
                        val job = async(Dispatchers.IO) {
                            viewModel.selectAllCompetencias()
                        }
                        job.await()
                        navController.navigate("evaluate")
                    }
                    //Passamos el nombre del modulo a la pantalla de evaluacion
                    Log.e("ModulItem", "click")
                }
            }
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                Modifier
                    .weight(1f)
                    .padding(16.dp)
            )
            Icon(
                Icons.Filled.ArrowForwardIos,
                contentDescription ="Continue",
                modifier = Modifier
                    .weight(0.2f)
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )

        }
    Spacer(modifier = Modifier.padding(8.dp))
    }

}
