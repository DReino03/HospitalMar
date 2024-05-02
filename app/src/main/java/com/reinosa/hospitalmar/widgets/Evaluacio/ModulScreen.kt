package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.HmViewmodel


@Composable
fun ModulScreen(navController: NavController, viewModel: HmViewmodel) {
    viewModel.getModulos()
    val modulList = viewModel.modulList.value

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                "Módulos",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        modulList?.let { list ->
            items(list.size) { index ->
                val modulo = list[index]
                ModulItem(text = modulo.nombreModul, navController = navController)
            }
        }
    }
}

