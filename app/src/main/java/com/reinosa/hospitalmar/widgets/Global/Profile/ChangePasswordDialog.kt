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
import androidx.compose.runtime.*
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.reinosa.hospitalmar.Model.SharedPreferences.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordDialog(
    showDialog: MutableState<Boolean>,
    viewModel: LoginViewModel,
    navController : NavController,
    onConfirm: () -> Unit
) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


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
                                if (newPassword == repeatPassword) {
                                    viewModel.updatePasswordAlumno(newPassword)
                                    coroutineScope.launch {
                                        Toast.makeText(
                                            context,
                                            "¡Contrasenya canviada correctament!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        showDialog.value = false
                                        onConfirm()
                                        UserPreferences.clearCredentials(context)
                                        navController.navigate("login")
                                    }
                                }
                                else{
                                    coroutineScope.launch {
                                        Toast.makeText(
                                            context,
                                            "Les contrasenyes noves no coincideixen",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                            else{
                                coroutineScope.launch {
                                    Toast.makeText(
                                        context,
                                        "La contrasenya antiga no coincideix",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                        else{
                            if (viewModel.getMd5DigestForPassword(oldPassword) == viewModel.currentProfesor.value!!.contrasenya){
//                            canChange = true
                                if (newPassword == repeatPassword) {
                                    viewModel.updatePasswordProfesor(newPassword)
                                    coroutineScope.launch {
                                        Toast.makeText(
                                            context,
                                            "¡Contrasenya canviada correctament!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        navController.navigate("login")
                                        showDialog.value = false
                                        onConfirm()
                                        UserPreferences.clearCredentials(context)
                                    }
                                }
                                else{
                                    coroutineScope.launch {
                                        Toast.makeText(
                                            context,
                                            "Les contrasenyes noves no coincideixen",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                            else{
                                coroutineScope.launch {
                                    Toast.makeText(
                                        context,
                                        "La contrasenya antiga no coincideix",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
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
