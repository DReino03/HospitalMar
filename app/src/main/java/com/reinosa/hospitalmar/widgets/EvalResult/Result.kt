package com.reinosa.hospitalmar.widgets.EvalResult

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.widgets.Drawer.DrawerHeader
import com.reinosa.hospitalmar.widgets.Drawer.DrawerItems
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Result(navController: NavController) {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun Result(navController: NavController) {
        val modul: String = remember {
            navController.previousBackStackEntry!!.arguments?.getString("modul")
                ?: "No proporcionado"
        }
        val persona: List<String>? = remember {
            navController.previousBackStackEntry?.arguments?.getString("persona")?.split(",")
        }
        val rating: List<Pair<String, Int>>? = remember {
            navController.previousBackStackEntry?.arguments?.getString("rating")?.split(",")
                ?.map { it.split(":").let { pair -> Pair(pair[0], pair[1].toInt()) } }
        }
        // rest of the code...

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val scope = rememberCoroutineScope()

        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.Result),
                            textAlign = TextAlign.Justify
                        )
                    },
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

                }
            },
            drawerBackgroundColor = Color.White // Cambiar por el color deseado
        ) {
            ResultContent(navController)
        }

    }
}