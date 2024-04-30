package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.reinosa.hospitalmar.Model.DataClass.EvalCard

class Star(val id: Int, var selected: Boolean)

@Composable
fun StarMenu(stars: Int, evalCard: EvalCard) {
    var selectedStar by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in 1..stars) {
            IconButton(
                onClick = {
                    selectedStar = i
                    evalCard.starRating = i
                    evalCard.rating = "Rating for ${evalCard.text}: $i"
                }
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