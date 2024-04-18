package com.reinosa.hospitalmar.widgets.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.ApiInterface.Repository
import com.reinosa.hospitalmar.Model.Credentials.Credentials
import com.reinosa.hospitalmar.Model.Credentials.checkCredentials
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.ui.theme.blueproject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginForm(navController: NavController, viewModel: LoginViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember {
        mutableStateOf("")
    }
    var hasedPassword by remember { mutableStateOf("") }

    var isChecked by remember { mutableStateOf(viewModel.isChecked) }
    var credentials by remember { mutableStateOf(Credentials()) }
    val context = LocalContext.current
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(blueproject)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                tint = colorResource(id = R.color.white),
                modifier = Modifier.size(100.dp)
            )
            loginField(
                value = username,
                onChange = { username = it },
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
                    label = "Remember Me",
                    onCheckChanged = {
                        credentials = credentials.copy(remember = !credentials.remember)
                    },
                    isChecked = credentials.remember
                )
            }

            Button(
                onClick = {
                    credentials = credentials.copy(remember = !credentials.remember)
                    navController.navigate("Drawer")
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clickable { hasedPassword = viewModel.hashPassword(password) }
            ) {
                Text("Accedeix" )
            }
        }
    }

    CoroutineScope(Dispatchers.IO).launch {
        val repository = Repository(username, hasedPassword)
        val response = repository.login(viewModel.currentAlumno.value!!)

        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.getUsuario(username)

                    withContext(Dispatchers.Main) {
                        viewModel.success.observe(viewLifecycleOwner) { success ->
                            if (success == true) {
                                findNavController().navigate(R.id.action_loginFragment_to_equipoFragment)
                            }
                        }
                    }
                }
            }else{
                val customToastView = layoutInflater.inflate(R.layout.toast_nologin, null)
                val toast = Toast(context)
                toast.duration = Toast.LENGTH_SHORT
                toast.view = customToastView
                toast.show()
            }
        }
    }
}