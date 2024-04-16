package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EvalScreen(navController: NavController) {

    Column {
        Text(text = "Iniciativa", modifier = Modifier
            .padding(16.dp)
            .background(Color.Green))
        LazyColumn {

            items(5)
            {index->    Card(
                modifier = Modifier
                    .background(Color.Blue)
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
                    StarMenu(5)
                }
                Spacer(modifier = Modifier.padding(8.dp))

            }

            }


        }
    }


}