package com.reinosa.hospitalmar.widgets.Informe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.ViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InformeScreen(navController: NavController, loginViewModel: ViewModel) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier
                .height(16.dp)
            )
            androidx.compose.material3.Text(
                text = "Calificaciones:",
                style = MaterialTheme.typography.h5
            )
            LazyColumn {
                items(10) {
                    CardInfo()
                }

            }
        }
    }
