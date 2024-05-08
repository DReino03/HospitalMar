package com.reinosa.hospitalmar.widgets.ProfEvaluate

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.LoginViewModel

@Composable

fun EvalContent(navController: NavController, coevalViewModel: LoginViewModel) {

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text("Alumnes", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp)) }
        items(10) {
            StudentCard(text = "Persona", navController = navController, coevalViewModel = LoginViewModel())
        }
    }

}
