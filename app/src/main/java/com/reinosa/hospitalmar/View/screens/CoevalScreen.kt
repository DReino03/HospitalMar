package com.reinosa.hospitalmar.View.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.widgets.Drawer.DrawerHeader
import com.reinosa.hospitalmar.widgets.Drawer.DrawerItems
import com.reinosa.hospitalmar.widgets.Evaluacio.ModulScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CoevalScreen(navController: NavController){
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.drawer_graphics), textAlign = TextAlign.Justify) },
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
    ){
    }
}



