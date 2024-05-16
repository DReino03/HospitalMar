package com.reinosa.hospitalmar.widgets.Drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.ViewModel
import com.reinosa.hospitalmar.ui.theme.blueproject

@Composable
fun DrawerHeader(viewModel: ViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(180.dp)
            .background(blueproject.copy(alpha = 0.8f))
            .padding(16.dp)
        ,
        contentAlignment = Alignment.BottomStart,

        ){
        Column {
            Image(
                painter = painterResource(R.drawable.ic_person),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(80.dp)
                      // add a border (optional)
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (!viewModel.isAlumno){
                Text(
                    text = viewModel.currentProfesor.value!!.nombre,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                )
                Text(text = viewModel.currentProfesor.value!!.identificador, style = MaterialTheme.typography.h6)
            }
            else{
                Text(
                    text = viewModel.currentAlumno.value!!.nombre,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                )
                Text(text = viewModel.currentAlumno.value!!.identificador, style = MaterialTheme.typography.h6)
            }


        }
    }
}
