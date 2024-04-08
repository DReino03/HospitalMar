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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.ui.theme.blueproject

@Composable
fun LoginForm(navController: NavController, viewModel: LoginViewModel) {
    var isChecked by remember { mutableStateOf(viewModel.isChecked) }

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
            LoginField(
                value = "login",
                onChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(
                value = "password",
                onChange = { },
                submit = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.padding(100.dp,0.dp,10.dp,0.dp)) {
                LabeledCheckbox(
                    label = "Remember Me",
                    isChecked = isChecked,
                    onCheckChanged = {viewModel.isChecked = isChecked},
                )
            }

            Button(
                onClick = {
                    navController.navigate("Drawer")
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text("Login" )
            }
        }
    }
}