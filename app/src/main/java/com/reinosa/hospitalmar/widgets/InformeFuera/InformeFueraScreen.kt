package com.reinosa.hospitalmar.widgets.InformeFuera

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DataClass.Informe
import com.reinosa.hospitalmar.ViewModel.ViewModel
import com.reinosa.hospitalmar.widgets.Modulos.ModulItem

@Composable
fun InformeFueraScreen(navController: NavController, viewModel: ViewModel) {

    Log.d("current alumno", viewModel.alumnoSelected.toString())

    val informeList = viewModel.informeList.value

    Log.d("Informes", viewModel.informeList.value.toString())

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                "Informes",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
        }
        informeList?.let { list ->
            items(list.size) { index ->
                val informe = list[index]
                InformeFueraItem(informe.fechaGeneracion, navController, viewModel, informe)
            }
        }
    }
}