package com.reinosa.hospitalmar.widgets.Competencias

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.LoginViewModel

@Composable
fun CompetenciasContainer(navController: NavController, viewModel: LoginViewModel) {
    Log.d("current alumno", viewModel.alumnoSelected.toString())

    val competenciaList = viewModel.competenciaList.value

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                        }
                    }
                )
            },
            drawerContent = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    DrawerHeader()
                    DrawerItems(navController = navController)

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
                "Competencias",
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
