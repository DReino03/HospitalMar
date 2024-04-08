package com.reinosa.hospitalmar.View.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R

class Star(val id: Int, var selected: Boolean)
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
                        .background(Color.Magenta)
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
@Composable
fun evalCard(text:String){
    Spacer(modifier = Modifier.padding(8.dp))
    Card(
        modifier = Modifier
            .background(Color.Magenta)
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

@Composable
fun StarMenu(stars: Int) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in 1..stars) {
            IconButton(
                onClick = { /* ... */ }
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Star,
                    contentDescription = "Star"
                )
            }
        }
    }
}
@Composable
fun Star(star: Star) {
    Box(
        modifier = Modifier.clickable { star.selected = !star.selected }
    ) {
        if (star.selected) {
            // Mostrar imagen o icono de estrella seleccionada
            this.let {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star"
                )
            }
        } else {
            // Mostrar imagen o icono de estrella no seleccionada
        }
    }
}

@Composable
fun StarMenu(stars: List<Star>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        stars.forEach { star ->
            Star(star)
        }
    }
}