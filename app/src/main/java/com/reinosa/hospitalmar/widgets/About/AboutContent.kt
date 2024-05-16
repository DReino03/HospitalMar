package com.reinosa.hospitalmar.widgets.About

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reinosa.hospitalmar.ui.theme.PurpleGrey80
import com.reinosa.hospitalmar.ui.theme.blueproject
import com.reinosa.hospitalmar.ui.theme.gris

@Composable
fun aboutContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(32.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gris)
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(
                    text = "APP Hospital del Mar consisteix en desenvolupar una aplicació mòbil per a l'avaluació de les diferents habilitats dels alumnes en l'àrea de salut, amb la finalitat d'integrar totes les puntuacions en un sistema centralitzat. Tenim l'objectiu de crear una aplicació intuïtiva per a l'avaluació de competències transversals mitjançant rúbriques validades, permetent així el seguiment del progrés de l'alumne en funció del seu nivell objectiu.\n" +
                            "\n" +
                            "La nostra proposta inclou la generació d'un informe final sobre l'estat de l'alumne, amb un enfocament especial en la protecció de les dades personals. L'aplicació estarà disponible en tauletes i proporcionarà funcionalitats pràctiques com ara el registre d'alumnes, l'assignació de rúbriques i la generació d'informes de manera senzilla i eficient.\n" +
                            "\n" +
                            "Aquesta aplicació està específicament dissenyada per a la seva implementació en l'entorn de l'Hospital del Mar, assegurant la seva adaptabilitat i utilitat en aquest context específic.",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    //fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}





