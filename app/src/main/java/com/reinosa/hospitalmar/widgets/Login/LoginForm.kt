package com.reinosa.hospitalmar.widgets.Login

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.ApiInterface.Repository
import com.reinosa.hospitalmar.Model.Credentials.Credentials
import com.reinosa.hospitalmar.Model.DataClass.Alumno
import com.reinosa.hospitalmar.Model.DataClass.Profesor
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.ui.theme.blueproject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.material.Surface
import com.reinosa.hospitalmar.Model.SharedPreferences.UserPreferences
import androidx.compose.runtime.*


@Composable
fun LoginForm(navController: NavController, viewModel: LoginViewModel) {
    var identificador by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Recuperar credenciales guardadas al cargar la pantalla
    LaunchedEffect(key1 = context) {
        val savedUsername = UserPreferences.getSavedUsername(context)
        val savedPassword = UserPreferences.getSavedPassword(context)
        if (savedUsername != null && savedPassword != null) {
            identificador = savedUsername
            password = savedPassword
            isChecked = true
        }
    }

    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(blueproject.copy(alpha = 0.8f))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_hospitalne),
                contentDescription = "Logo",
                modifier = Modifier.size(350.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            loginField(
                value = identificador,
                onChange = { identificador = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            passwordField(
                value = password,
                onChange = { password = it },
                submit = { /* if (!checkCredentials(credentials, context)) credentials = Credentials()*/},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .wrapContentSize(Alignment.Center)
            ) {
                LabeledCheckbox(
                    label = "Recorda'm",
                    onCheckChanged = { isChecked = !isChecked },
                    isChecked = isChecked
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val hashedPassword = viewModel.getMd5DigestForPassword(password)
                    viewModel.currentAlumno.value = Alumno(0, "", "", "", identificador, "", "", "", hashedPassword, 0)
                    Log.d("HASHPASSWORD", viewModel.currentAlumno.value!!.contrasenya.toString())
                    viewModel.currentProfesor.value = Profesor(0, "", "", "", identificador, "", "", "", hashedPassword, true, true)
                    viewModel.repository.value = Repository(identificador, hashedPassword)
                    Log.d("CONTRASENYA", hashedPassword)

                    CoroutineScope(Dispatchers.IO).launch {
                        val validarUsuario = identificador.contains(Regex(".*pr.*"))

                        val repository = Repository(identificador, hashedPassword)
                        val response = if (validarUsuario) {
                            repository.loginProfesor(viewModel.currentProfesor.value!!)
                        } else {
                            repository.loginAlumno(viewModel.currentAlumno.value!!)
                        }

                        withContext(Dispatchers.Main) {
                            if (response.isSuccessful) {
                                if (isChecked) {
                                    // Guardar las credenciales en SharedPreferences
                                    UserPreferences.saveCredentials(context, identificador, password)
                                }

                                if (validarUsuario) {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        val job = async(Dispatchers.IO) {
                                            viewModel.getProfesor(identificador)
                                        }
                                        job.await() // Esperar a que se complete la función getProfesor
                                        navController.navigate("home")
                                        Log.d("Usuarionv", viewModel.currentProfesor.value.toString())
                                    }
                                }
                                else {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        val job = async(Dispatchers.IO){
                                            viewModel.isAlumno = true
                                            viewModel.getAlumno(identificador)
                                        }
                                        job.await()
                                        navController.navigate("home")
                                        Log.d("Usuario66", viewModel.currentAlumno.value.toString())
                                    }
                                }
                            } else {
                                val toast = Toast.makeText(context, "Error", Toast.LENGTH_SHORT)
                                toast.show()
                            }
                        }
                    }
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .padding(20.dp),
                colors = ButtonDefaults.buttonColors(blueproject)
            ) {
                Text("Accedeix", style = TextStyle(fontWeight = FontWeight.Bold))
            }
        }
    }
}

