package com.reinosa.hospitalmar.widgets.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
          ListItem(
               headlineText = { Text("Home") },
            )
          ListItem(
                 headlineText = { Text("Profile") },
                )
          ListItem(
                 headlineText = { Text("Settings") },
                )


    }
}
