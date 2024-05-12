package com.reinosa.hospitalmar.widgets.Home


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.LoginViewModel

@Composable
fun TeacherHomeContent(navController: NavController, viewModel: LoginViewModel){


    Log.d("PROFESOR PANTALLA INCIO", viewModel.currentProfesor.value.toString())
    Log.d("alumno PANTALLA INCIO", viewModel.currentAlumno.value.toString())

    val imagePainter = listOf<Int>(R.drawable.ic_evaluacio,R.drawable.ic_autoavaluacio,R.drawable.ic_person,R.drawable.ic_informes )
    Column(modifier = Modifier
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
            Image(painter = painterResource(R.drawable.ic_hospital), contentDescription = "logo", modifier = Modifier.padding(20.dp))
        }
        Spacer(modifier = Modifier.padding(50.dp))
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeButton(navController, "Autoavaluació", imagePainter[0], "evaluate")
            Spacer(modifier = Modifier.padding(40.dp) )
            //Cambia esto
            HomeButton(navigation = navController, text = "Evaluar", imageRoute =imagePainter[1] , destination ="student" )
            viewModel.getAlumnosIdProfesor()


        }
        Spacer(modifier = Modifier.padding(30.dp))
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeButton(navController, "Perfil", imagePainter[2], "profile")
            Spacer(modifier = Modifier.padding(40.dp) )
            HomeButton(navController, "Informes" ,imagePainter[3], "informe")
        }
    }
}