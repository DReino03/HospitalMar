package com.reinosa.hospitalmar.widgets.ProfEvaluate

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.ViewModel


@Composable
fun StudentCard(text:String ,navController: NavController, coevalViewModel: ViewModel) {
    var borderColor = remember { mutableStateOf(Color.Transparent) } // Inicialmente transparente
    val studentsSelected = coevalViewModel.studentsSelected
    Card(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .fillMaxWidth()
            .border(2.dp, borderColor.value)
            .clickable {
                //selecciona a los estudiantes para la coevaluación y los añade a la lista de estudiantes seleccionados ademas marca el Card con un color de fondo
                Log.e("StudentItem", "click")
                navController.navigate("evaluate")
            },
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Row() {
            Text(
                text = text,
                Modifier.padding(16.dp))
            Spacer(modifier = Modifier.weight(0.9f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription ="ADD",
                    modifier = Modifier
                        .size(50.dp)

                )
            }

        }
        Spacer(modifier = Modifier.padding(8.dp))

    }

}

