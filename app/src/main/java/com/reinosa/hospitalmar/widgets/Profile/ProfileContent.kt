package com.reinosa.hospitalmar.widgets.Profile

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ui.theme.blueproject
import com.reinosa.hospitalmar.widgets.Drawer.DrawerItems

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Sección superior
        Box(
            modifier = Modifier
                .background(
                    color = blueproject,
                    shape = RoundedCornerShape(0.dp, 0.dp, 40.dp, 40.dp)
                )
                .padding(40.dp)
                .weight(0.60f) // 33% de la pantalla
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                // Foto de perfil
                Image(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                // Nombre de usuario
                Text(
                    text = "Nom usuari",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
                // Identificador de usuario
                Text(
                    text = "ibax000",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        // Sección inferior
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Cicle: Medicina",
                style = MaterialTheme.typography.subtitle1,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }



    @Composable
    fun UserProfileDetailItem(title: String, value: String) {
        Column {
            Text(text = title, style = MaterialTheme.typography.body1)
            Text(text = value, style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

