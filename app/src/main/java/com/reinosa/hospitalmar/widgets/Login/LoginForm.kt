package com.reinosa.hospitalmar.widgets.Login

import android.annotation.SuppressLint
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
                .background(blueproject)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_hospitalne),
                contentDescription = "Logo",
                modifier = Modifier.size(350.dp)
            )
            loginField(
                value = correo,
                onChange = { correo = it },
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
                    credentials = credentials.copy(remember = !credentials.remember)
                    viewModel.currentAlumno.value = Alumno(0, "", "", "", "", correo, "",0,0,password,0)
                    viewModel.repository = Repository(correo, hashedPassword)


                    CoroutineScope(Dispatchers.IO).launch {
                        val repository = Repository(correo, hashedPassword)
                        val response = repository.login(viewModel.currentAlumno.value!!)

                        withContext(Dispatchers.Main) {
                            if (response.isSuccessful) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    viewModel.getUsuario(correo)
                                    navController.navigate("drawer")

                                }
                            }else{
                                val toast = Toast(context)
                                toast.duration = Toast.LENGTH_SHORT
                                toast.setText("Error")
                                toast.show()
                            }
                        }
                    }
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            ) {
                Text("Accedeix")
            }
        }
    }




}