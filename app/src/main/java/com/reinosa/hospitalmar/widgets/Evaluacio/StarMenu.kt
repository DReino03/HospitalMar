package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class EvalCard(val text: String, var starRating: Int = 0)

@Composable
fun StarMenu(stars: Int) {
    var selectedStar by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in 1..stars) {
            IconButton(
                onClick = { selectedStar = i }
            ) {
                Icon(
                    imageVector = if (i <= selectedStar) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = "Star" ,
                    tint = if (i <= selectedStar) Color.Yellow else Color.Black
                )
            }
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
            com.reinosa.hospitalmar.widgets.Evaluacio.Star(star)
        }
    }
}

fun generateReport(evalCards: List<EvalCard>) {
    evalCards.forEach { card ->
        println("Texto: ${card.text}, Puntuación de estrellas: ${card.starRating}")
    }
}