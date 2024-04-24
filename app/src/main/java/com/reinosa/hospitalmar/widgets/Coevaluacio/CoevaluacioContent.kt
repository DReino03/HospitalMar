package com.reinosa.hospitalmar.widgets.Coevaluacio

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.CoevalViewModel
import com.reinosa.hospitalmar.widgets.Evaluacio.ModulItem

@Composable

fun CoevaluacioContent(navController: NavController, coevalViewModel: CoevalViewModel) {

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text("Alumnes", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp)) }
        items(10) {
            StudentItem(text = "Persona", navController = navController, coevalViewModel = CoevalViewModel())
        }
    }


}
