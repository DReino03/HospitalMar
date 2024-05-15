package com.reinosa.hospitalmar.widgets.Global.Profile


import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight


@Composable
fun ChangePasswordDialog(showDialog: MutableState<Boolean>, onConfirm: () -> Unit) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                Text(
                    text = "Canviar contrasenya",
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
                )
            },
            text = {

                Text(text = "Introduce tu nueva contraseña:")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                    }
                ) {
                    Text(text = "Guardar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog.value = false }
                ) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}