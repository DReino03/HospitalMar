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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.widgets.Drawer.DrawerItems

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileContent(navController: NavController) {
    // Contenido de la pantalla
    Surface(
        modifier = Modifier
            .size(154.dp)
            .padding(5.dp),
        shape = CircleShape,
        elevation = 4.dp,

    ) {
        Image(
            painter = painterResource(R.drawable.ic_person),
            contentDescription = "ic_person",
            modifier = Modifier.padding(20.dp))

    }

}

