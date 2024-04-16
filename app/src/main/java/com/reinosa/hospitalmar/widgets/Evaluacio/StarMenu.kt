package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


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