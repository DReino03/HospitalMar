package com.reinosa.hospitalmar.widgets.Competencias


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DataClass.Alumno
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.LoginViewModel

@Composable
fun CompetenciasItem(navController: NavController, viewModel: LoginViewModel) {
    var borderColor = remember { mutableStateOf(Color.Transparent) } // Inicialmente transparente
    Card(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            //.border(2.dp, borderColor.value)
            .clickable {
                //selecciona a los estudiantes para la coevaluación y los añade a la lista de estudiantes seleccionados ademas marca el Card con un color de fondo
                Log.e("StudentItem", "click")
                navController.navigate("modulo")
            },
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Competencias",
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            )
            Icon(
                Icons.Filled.ArrowForwardIos,
                contentDescription ="Continue",
                modifier = Modifier
                    .weight(0.2f) // Asigna un peso al icono
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
    }


}


