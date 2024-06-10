package com.reinosa.hospitalmar.widgets.Evaluacio

import DropdownMenuWidgett
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.ViewModel.ViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.reinosa.hospitalmar.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EvaluarScreen(navController: NavController, viewModel: ViewModel) {
    val ratings: MutableList<Pair<String, Int>> = mutableListOf()
    val comments = remember { mutableStateListOf<MutableList<String>>() }

    repeat(5) {
        comments.add(mutableListOf())
    }

    val competenciaSelected by viewModel.competenciaSelectedd.collectAsState()
    //val competencias by viewModel.competenciaListt.collectAsState()
    val displayText by viewModel.displayText.collectAsState()
    val competenciaInfo by viewModel.competenciaInfo.collectAsState()
        LazyColumn {
            item {
                Spacer(modifier = Modifier.padding(12.dp))
                 if (displayText.isNotEmpty()) {
                    Text(
                        text = displayText,
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        overflow = TextOverflow.Ellipsis,
                    )
                     Text(
                         text = competenciaInfo.descripcion,
                         style = MaterialTheme.typography.subtitle1,
                         modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                         overflow = TextOverflow.Ellipsis,
                     )
                }
                DropdownMenuWidgett(
                    navController = navController,
                    viewModel = viewModel,
                    onItemSelected = { viewModel.setSelectedCompetencia(it) },
                    modifier = Modifier,
                )

                if (displayText.isNotEmpty()) {
                    competenciaInfo?.let { competencia ->
                        repeat(4) { index ->
                            val pregunta = when (index) {
                                0 -> competencia.pregunta1
                                1 -> competencia.pregunta2
                                2 -> competencia.pregunta3
                                3 -> competencia.pregunta4
                                else -> ""
                            }
                            viewModel.listComentarios = comments
                            EvalItem(text = pregunta, index = index, comments = comments, viewModel = viewModel)
                        }
                    }
                }else{
                    EmptyState()
                }
            }


            item {
                // Añade las calificaciones a la lista
                competenciaSelected?.descripcion?.let { descripcion ->
                    repeat(5) {
                        val data = Pair(descripcion, 0)
                        ratings.add(data)
                    }
                }
            }
        }
    }

@Composable
fun EmptyState(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(28.dp))
        Text(
            text = "Selecciona una competencia",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.padding(42.dp))

        Image(painter = painterResource(id = R.drawable.ic_selectdropdown),
            contentDescription = "Icon",
            alignment = Alignment.Center,
            modifier = Modifier.padding(18.dp))

    }

}