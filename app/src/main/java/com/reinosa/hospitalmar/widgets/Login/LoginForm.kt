package com.reinosa.hospitalmar.widgets.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.reinosa.hospitalmar.Model.Credentials.Credentials
import com.reinosa.hospitalmar.Model.Credentials.checkCredentials
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.ui.theme.blueproject
import com.reinosa.hospitalmar.widgets.Login.LabeledCheckbox
import com.reinosa.hospitalmar.widgets.Login.loginField
import com.reinosa.hospitalmar.widgets.Login.passwordField

@Composable
fun LoginForm(navController: NavController, viewModel: LoginViewModel) {
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
                value = "login",
                onChange = { data -> credentials = credentials.copy(login = data) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            )
            Spacer(modifier = Modifier.height(10.dp))
            passwordField(
                value = "password",
                onChange = { data -> credentials = credentials.copy(pwd = data) },
                submit = { if (!checkCredentials(credentials, context)) credentials = Credentials() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.padding(100.dp,0.dp,10.dp,0.dp)) {
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
            ) {
                Text("Accedeix" )
            }
        }
    }
}