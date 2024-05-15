package com.reinosa.hospitalmar.widgets.Competencias

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.widgets.Modulos.ModulItem

@Composable
fun CompetenciasScreen(navController: NavController, viewModel: LoginViewModel) {

    val competenciaList = viewModel.competenciaList.value

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                text = viewModel.moduloSelected!!.nombreModulo,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                "Competències",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
        }
        competenciaList?.let { list ->
            items(list.size) { index ->
                val competencia = list[index]
                CompetenciasItem(text = competencia.nombreCompetencia, navController = navController, viewModel, competencia)
            }
        }
    }
}
