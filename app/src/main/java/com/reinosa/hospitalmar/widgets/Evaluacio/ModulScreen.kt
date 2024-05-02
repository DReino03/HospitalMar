package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DataClass.Modulo
import com.reinosa.hospitalmar.ViewModel.HmViewmodel


@Composable
fun ModulScreen(navController: NavController, viewModel: HmViewmodel) {
//    // Estado para almacenar la lista de módulos
//    var moduloList by remember { mutableStateOf<List<String>>(emptyList()) }
//
//    // Llama a la función para cargar los módulos cuando el composable se active
//    LaunchedEffect(key1 = true) {
//        viewModel.getModulos()
//    }
//
//    // Observa la lista de módulos en el ViewModel y actualiza el estado local
//    viewModel.modulList.observeAsState().value?.let { list ->
//        moduloList = list.map { modulo -> modulo.nombre } // Suponiendo que 'nombre' es el campo que deseas mostrar en el ModulItem
//    }
//
//    LazyColumn {
//        item {
//            Spacer(modifier = Modifier.padding(12.dp))
//            Text("Módulos", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))
//        }
//        items(moduloList) { modulo ->
//            ModulItem(text = modulo, navController = navController)
//        }
//    }
}

