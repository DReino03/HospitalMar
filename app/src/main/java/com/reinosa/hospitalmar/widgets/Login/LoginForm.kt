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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginForm(navController: NavController, viewModel: LoginViewModel) {
    var correo by remember { mutableStateOf("") }
    var identificador by remember { mutableStateOf("") }
    var password by remember {
        mutableStateOf("")
    }
    var hashedPassword by remember { mutableStateOf("") }

    var isChecked by remember { mutableStateOf(viewModel.isChecked) }
    var credentials by remember { mutableStateOf(Credentials()) }
    val context = LocalContext.current
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
                modifier = Modifier.size(350.dp)
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
                onChange = { data -> credentials = credentials.copy(pwd = data) },
                submit = { /* if (!checkCredentials(credentials, context)) credentials = Credentials()*/},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .wrapContentSize(Alignment.Center)) {
                LabeledCheckbox(
                    label = "Recorda'm",
                    onCheckChanged = {
                        credentials = credentials.copy(remember = !credentials.remember)
                    },
                    isChecked = credentials.remember
                )
            }

            Button(
                onClick = {
                    hashedPassword = viewModel.hashPassword(password)
                    viewModel.currentAlumno.value = Alumno(0, "", "", "", identificador, "", "","",hashedPassword,0)
                    //HAY QUE CREAR UN COURRENT PROFESOR PARA PODER HACER EL LOGIN DEKL PROFESOR
                    viewModel.currentProfesor.value = Profesor(0, "", "", "", identificador, "", "", "", hashedPassword, true, true)
                    viewModel.repository = Repository(identificador, hashedPassword)
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
                                if (validarUsuario) {
                                    viewModel.getProfesor(identificador)
                                    navController.navigate("evaluate")
                                    Log.d("Usuario", viewModel.currentProfesor.value.toString())

                                } else {
                                    viewModel.getAlumno(identificador)
                                    navController.navigate("drawer")
                                    Log.d("Usuario", viewModel.currentAlumno.value.toString())
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
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text("Accedeix")
            }
        }
    }




}