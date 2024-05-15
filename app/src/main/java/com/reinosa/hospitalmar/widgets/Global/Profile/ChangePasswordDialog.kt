import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.ui.theme.blueproject

import com.reinosa.hospitalmar.widgets.Login.passwordField

@Composable
fun ChangePasswordDialog(showDialog: MutableState<Boolean>, viewModel: LoginViewModel, onConfirm: () -> Unit) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var canChange = false

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            backgroundColor = Color.LightGray,
            text = {
                Column(
                    modifier = Modifier.padding(vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Canviar contrasenya",
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    passwordField(
                        value = oldPassword,
                        onChange = { oldPassword = it },
                        submit = { },
                        label = "Contrasenya antiga",
                        labelFontSize = 12.sp // Tamaño de fuente más pequeño
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    passwordField(
                        value = newPassword,
                        onChange = { newPassword = it },
                        submit = { /* Función nueva contraseña */ },
                        label = "Nova contrasenya",
                        labelFontSize = 12.sp // Tamaño de fuente más pequeño
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    passwordField(
                        value = repeatPassword,
                        onChange = { repeatPassword = it },
                        submit = { /* Funciónrepetir nueva contraseña */ },
                        label = "Repetir contrasenya",
                        labelFontSize = 6.sp // Tamaño de fuente más pequeño
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (viewModel.isAlumno){
                            if (viewModel.getMd5DigestForPassword(oldPassword) == viewModel.currentAlumno.value!!.contrasenya){
//                            canChange = true
                                if (newPassword == repeatPassword) {
                                    viewModel.updatePasswordAlumno(newPassword)
                                }
                                else{
                                    Log.d("Contrasenyas no son iguales", "jeje")
                                }
                            }
                            else{
                                Log.d("Contrasenya antigua incorrecta", "jeje")
                            }
                        }
                        else{
                            if (viewModel.getMd5DigestForPassword(oldPassword) == viewModel.currentProfesor.value!!.contrasenya){
//                            canChange = true
                                if (newPassword == repeatPassword) {
                                    viewModel.updatePasswordProfesor(newPassword)
                                }
                                else{
                                    Log.d("Contrasenyas no son iguales", "jeje")
                                }
                            }
                            else{
                                Log.d("Contrasenya antigua incorrecta", "jeje")
                            }
                        }
                        showDialog.value = false
                        onConfirm()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = blueproject.copy(alpha = 0.8f)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.wrapContentWidth().padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Guardar",
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                    )

                }
            })
    }
}
