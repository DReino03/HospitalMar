package com.reinosa.hospitalmar.widgets.Evaluacio

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import com.reinosa.hospitalmar.widgets.Modulos.ModulItem

@Composable
fun ModulScreen(navController: NavController, viewModel: LoginViewModel) {

    Log.d("current alumno", viewModel.alumnoSelected.toString())

    val modulList = viewModel.modulList.value

    Log.d("Modulos", viewModel.modulList.value.toString())

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                text = viewModel.alumnoSelected!!.especialidad,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                "Mòduls",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
        }
        modulList?.let { list ->
            items(list.size) { index ->
                val modul = list[index]
                ModulItem(text = modul.nombreModulo, navController = navController, viewModel, modul)
            }
        }
    }
}



