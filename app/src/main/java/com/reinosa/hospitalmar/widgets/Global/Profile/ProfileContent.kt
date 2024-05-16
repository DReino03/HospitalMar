package com.reinosa.hospitalmar.widgets.Global.Profile

import ChangePasswordDialog
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.SharedPreferences.UserPreferences
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.ViewModel
import com.reinosa.hospitalmar.ui.theme.blueproject


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileContent(navController: NavController, viewModel: ViewModel) {
    var identificador by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
    ) {
        // Sección superior
        Box(
            modifier = Modifier
                .background(
                    color = blueproject.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(0.dp, 0.dp, 40.dp, 40.dp)
                )
                .padding(20.dp)
                .weight(0.80f)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                // Foto de perfil
                Image(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                if (!viewModel.isAlumno) {
                    // Nombre de usuario
                    Text(
                        text = viewModel.currentProfesor.value!!.nombre,
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold, fontSize = 25.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    // Identificador de usuario
                    Text(
                        text = viewModel.currentProfesor.value!!.identificador,
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Button(
                        onClick = {
                            showDialog.value = true
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier.padding(8.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(text = "Canviar contrasenya")
                    }
                } else {
                    // Nombre de usuario
                    Text(
                        text = viewModel.currentAlumno.value!!.nombre,
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold, fontSize = 25.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    // Identificador de usuario
                    Text(
                        text = viewModel.currentAlumno.value!!.identificador,
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Button(
                        onClick = {
                            showDialog.value = true
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier.padding(8.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(text = "Canviar contrasenya")
                    }
                }
            }
        }
        if (!viewModel.isAlumno) {
            // Sección inferior
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 40.dp)
            ) {
                Text(
                    text = "Dades personals:", // Cambio de texto
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Text(
                    text = "Nom complet : ${viewModel.currentProfesor.value!!.nombre} ${viewModel.currentProfesor.value!!.apellidos}",
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = "Correu: ${viewModel.currentProfesor.value!!.correo}",
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

            }
        } else {
            // Sección inferior
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 40.dp)
            ) {
                Text(
                    text = "Dades personals:", // Cambio de texto
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Text(
                    text = "Nom complet : ${viewModel.currentAlumno.value!!.nombre} ${viewModel.currentAlumno.value!!.apellidos}",
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = "Cicle: ${viewModel.currentAlumno.value!!.especialidad}",
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = "Correu: ${viewModel.currentAlumno.value!!.correo}",
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

            }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    UserPreferences.clearCredentials(context)
                    identificador = ""
                    password = ""
                    isChecked = false
                    navController.navigate("login")
                    viewModel.isAlumno = false
                },
                colors = ButtonDefaults.buttonColors(blueproject.copy(alpha = 0.8f)),
                modifier = Modifier.padding(end = 8.dp, bottom = 100.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Tancar sessió",
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                )

            }
            ChangePasswordDialog(showDialog = showDialog, viewModel, navController) {
                showDialog.value = false
            }
        }
    }
}




