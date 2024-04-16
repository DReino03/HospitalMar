package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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