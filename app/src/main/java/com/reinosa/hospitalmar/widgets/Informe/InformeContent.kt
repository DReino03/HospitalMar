package com.reinosa.hospitalmar.widgets.Informe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.LoginViewModel

@Composable
fun InformeContent(navController: NavController, loginViewModel: LoginViewModel) {

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier
            .height(16.dp)
        )
        Text(text = "Calificaciones:",
            style = MaterialTheme.typography.h5
        )
        LazyColumn {
            items(10) {
                CardInfo()
            }

        }
    }
}