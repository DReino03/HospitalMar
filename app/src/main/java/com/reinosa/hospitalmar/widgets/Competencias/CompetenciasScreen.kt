package com.reinosa.hospitalmar.widgets.Competencias

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
import com.reinosa.hospitalmar.ViewModel.ViewModel

@Composable
fun CompetenciasScreen(navController: NavController, viewModel: ViewModel) {

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
