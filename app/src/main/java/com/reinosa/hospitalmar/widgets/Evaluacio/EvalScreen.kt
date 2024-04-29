package com.reinosa.hospitalmar.widgets.Evaluacio

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.widgets.Drawer.DrawerHeader
import com.reinosa.hospitalmar.widgets.Drawer.DrawerItems
import kotlinx.coroutines.launch
import com.reinosa.hospitalmar.widgets.Evaluacio.StarMenu

@Composable
fun EvalItem(modul: String, ratings: MutableList<Int>) {

    Column {

        LazyColumn {
            item {
                Spacer(modifier = Modifier.padding(12.dp))
                Text("Iniciativa", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp)) }

            items(5) {index->
                val selectedStar = remember { mutableStateOf(0) }
                Card(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                Spacer(modifier = Modifier.padding(8.dp))
                Row() {
                    Text(
                        text = "Actua amb rapidesa per a resoldre els problemes que es presenten a la tasca",
                        Modifier.padding(16.dp))
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Row(
                ) {
                    StarMenu(4)
                }
                Spacer(modifier = Modifier.padding(8.dp))

            }
                ratings.add(selectedStar.value)

            }


        }
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EvalScreen(navController: NavController, string: String?) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val modul = string ?: "Modul"
    Log.e("EvalScreen", modul)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    androidx.compose.material.Text(
                        text = stringResource(R.string.drawer_graphics),
                        textAlign = TextAlign.Justify)
                        },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Localized description")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("result")
                        val toast = Toast(context)
                        toast.duration = Toast.LENGTH_SHORT
                        toast.setText("Guardado Correctamente")
                        toast.show()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        drawerContent = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                DrawerHeader()
                DrawerItems(navController = navController)

            }
        },
        drawerBackgroundColor = Color.White // Cambiar por el color deseado
    ){
        EvalItem(modul, mutableListOf())
    }

}