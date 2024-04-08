package com.reinosa.hospitalmar.View


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.reinosa.hospitalmar.Model.AppDrawerItemInfo
import com.reinosa.hospitalmar.Model.DrawerParams
import com.reinosa.hospitalmar.R
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DrawerAppScreen(navController: NavHostController) {
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
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.Cyan,
                content = {
                    Text("BottomBar")
                }
            )
        },
        drawerContent = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                DrawerHeader()
                DrawerItems()
            }
        },
        drawerBackgroundColor = Color.White // Cambiar por el color deseado
    ){
        Text("Bienvenido a Hospital Mar", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.h2)


    }
}


@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.Blue)
            .padding(16.dp)
        ,
        contentAlignment = Alignment.BottomStart,

        ){
        Column {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Maria Lorente",
                style = MaterialTheme.typography.h5,
                color = Color.White,
            )
            Text(text = "ibaux00xxx", style = MaterialTheme.typography.h6)
        }
    }
}

@Composable
fun DrawerItems(navController: Navigation? = null) {
    for (item in DrawerParams.drawerButtons) {
        DrawerItem(item, navController)
    }
}

@Composable
fun DrawerItem(item: AppDrawerItemInfo<*>, navController: Navigation?) {
    val navController = rememberNavController()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(48.dp)
            .clickable {
                val routeName =
                    item.route.toString() // Assuming route names match MainNavOption values
                Log.e("DrawerItem", "-------Navigating to $routeName")
                navController.navigate(routeName)

            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = item.drawableId),
            contentDescription = stringResource(id = item.descriptionId),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = item.title),
            style = MaterialTheme.typography.body1
        )
    }
}




