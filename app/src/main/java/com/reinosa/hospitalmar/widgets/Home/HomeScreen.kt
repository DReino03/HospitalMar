package com.reinosa.hospitalmar.widgets.Home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerScreen() {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Title") }
            )
        },
        drawerContent = {
            DrawerContent(selectedItem) { index ->
                selectedItem = index
            }
        },
        content = {
            when (selectedItem) {
                0 -> ScreenContent("Home Screen")
                1 -> ScreenContent("Profile Screen")
                2 -> ScreenContent("Settings Screen")
            }
        }
    )
}

