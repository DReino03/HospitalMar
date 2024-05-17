package com.reinosa.hospitalmar.widgets.Home


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeContent(navController: NavController, viewModel: ViewModel) {


    Log.d("PROFESOR PANTALLA INCIO", viewModel.currentProfesor.value.toString())
    Log.d("alumno PANTALLA INCIO", viewModel.currentAlumno.value.toString())

    val imagePainter = listOf<Int>(
        R.drawable.ic_evaluacio,
        R.drawable.ic_autoavaluacio,
        R.drawable.ic_person,
        R.drawable.ic_informes
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_hospital),
                contentDescription = "logo",
                modifier = Modifier.padding(20.dp)
            )
        }
        Spacer(modifier = Modifier.padding(50.dp))
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //HomeButton(navController, "Autoavaluació", imagePainter[0], "evaluate")

            //Cambia esto
            if (!viewModel.isAlumno){
                HomeButton(navController, "Avaluar", imagePainter[1] , "student", viewModel )
                viewModel.getAlumnosIdProfesor()
            }
            if (viewModel.isAlumno){
                Text("Benvingut/da ${viewModel.currentAlumno.value!!.nombre} !",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeButton(navController, "Perfil", imagePainter[2], "profile", viewModel)
            Spacer(modifier = Modifier.padding(40.dp))
            if (viewModel.isAlumno) {
                CoroutineScope(Dispatchers.Main).launch {
                    val job = async(Dispatchers.IO) {
                        viewModel.selectModuloPorCiclo(viewModel.currentAlumno.value!!.etiqueta)
                    }
                    job.await() // Esperar a que se complete la función getProfesor
                }
                HomeButton(navController, "Informes", imagePainter[3], "modulo", viewModel)
            } else {
                HomeButton(navController, "Informes", imagePainter[3], "student", viewModel)
            }
        }
    }
}