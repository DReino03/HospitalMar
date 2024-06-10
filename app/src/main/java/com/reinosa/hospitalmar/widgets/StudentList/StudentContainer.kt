package com.reinosa.hospitalmar.widgets.StudentList

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HighlightOff
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DataClass.Alumno
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.ViewModel
import com.reinosa.hospitalmar.ui.theme.blueproject
import com.reinosa.hospitalmar.widgets.Drawer.DrawerHeader
import com.reinosa.hospitalmar.widgets.Drawer.DrawerItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StudentContainer(navController: NavController, viewModel: ViewModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    // Collecting states from ViewModel
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val studentsList by viewModel.studentsList.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.Evaluar)) },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onToogleSearch()
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "Localized description")
                    }
                },
                backgroundColor = blueproject.copy(alpha = 0.8f)
            )
        },
        drawerContent = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                DrawerHeader(viewModel)
                DrawerItems(navController = navController)
            }
        },
        drawerBackgroundColor = Color.White // Cambiar por el color deseado
    ) {
        Column {
            // Barra de búsqueda
            AnimatedVisibility(visible = isSearching) {
                TextField(
                    value = searchText,
                    onValueChange = { viewModel.onSearchTextChange(it) },
                    label = { Text("Buscar") },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    leadingIcon = {
                        IconButton(onClick = {
                            viewModel.onSearchTextChange("")
                        }) {
                            Icon(Icons.Filled.HighlightOff, contentDescription = "Localized description", modifier = Modifier.clickable { viewModel.onToogleSearch() })
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )

            }
            // Lista de estudiantes
            StudentList(navController = navController, viewModel = viewModel, searchValue = searchText)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar(viewModel: ViewModel, searchText: String, isSearching: Boolean) {
    SearchBar(
        query = searchText,
        onQueryChange = viewModel::onSearchTextChange,
        onSearch = viewModel::onSearchTextChange,
        active = isSearching,
        onActiveChange = { viewModel.onToogleSearch() },


    ) {
    }
}
