package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class Star(val id: Int, var selected: Boolean)

@Composable
fun evalCard(text:String){
    Spacer(modifier = Modifier.padding(8.dp))
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
            StarMenu(5)
        }
        Spacer(modifier = Modifier.padding(8.dp))

    }
}