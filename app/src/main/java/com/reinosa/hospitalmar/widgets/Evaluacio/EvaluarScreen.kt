package com.reinosa.hospitalmar.widgets.Evaluacio

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.widgets.Drawer.DrawerHeader
import com.reinosa.hospitalmar.widgets.Drawer.DrawerItems
import com.reinosa.hospitalmar.widgets.Evaluacio.EvalItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EvaluarScreen(navController: NavController, loginViewModel: LoginViewModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val modul : String = if (navController.currentBackStackEntry != null) {
        navController.currentBackStackEntry?.arguments?.getString("modul") ?: ""
    } else {
        "sin modul"
    }
    Log.e("EvalScreen", modul)

   Scaffold(
    scaffoldState = scaffoldState,
    bottomBar = {
        Column {
            Text(
                text = "Modulo: $modul",
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6
            )
        }
    },
    topBar = {
        TopAppBar(
            backgroundColor = Color(0xFF303F9F), // Color de la barra superior
            title = {
                Text(
                    text = stringResource(R.string.drawer_graphics),
                    textAlign = TextAlign.Justify,
                    color = Color.White) // Color del texto
            },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Localized description",
                        tint = Color.White) // Color del ícono
                }
            },
            actions = {
                IconButton(onClick = {
                    val toast = Toast(context)
                    toast.duration = Toast.LENGTH_SHORT
                    toast.setText("Guardado Correctamente")
                    toast.show()
                    navController.navigate("informe")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = "Localized description",
                        tint = Color.White) // Color del ícono
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
    drawerBackgroundColor = Color(0xFFF5F5F5) // Color de fondo del cajón
){
    EvalItem(loginViewModel)
}

}