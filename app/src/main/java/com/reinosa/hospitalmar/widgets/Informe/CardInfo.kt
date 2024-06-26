package com.reinosa.hospitalmar.widgets.Informe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.reinosa.hospitalmar.Model.DataClass.EvalCard
import com.reinosa.hospitalmar.ViewModel.ViewModel


@Composable
fun CardInfo(comentario: String, viewModel: ViewModel, nota: Int, pregunta: String) {
    val evalCard = EvalCard("Modul", 0, "Rating for Modul: 0")
    var comment = true
    Card(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Row() {
                Text(
                    text = pregunta,
                    Modifier
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(0.6f))
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Row {
                Spacer(modifier = Modifier.padding(8.dp))
                StaticStar(nota)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            //si hay comentarios se muestran desplegando la tarjeta con un background mas oscuro si no hay comentarios dejar como esta
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = comentario,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.padding(8.dp))

        }

    }
}


@Composable
fun StaticStar(selectedStar: Int) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in 1..4) {
                Icon(
                    modifier = Modifier.size(34.dp),
                    imageVector = if (i <= selectedStar) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = "Star",
                    tint = if (i <= selectedStar) Color.Yellow else Color.Black
                )
                Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}