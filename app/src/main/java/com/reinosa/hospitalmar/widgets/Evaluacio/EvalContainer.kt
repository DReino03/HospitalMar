package com.reinosa.hospitalmar.widgets.Evaluacio

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DataClass.Informe
import com.reinosa.hospitalmar.Model.DataClass.Nota
import com.reinosa.hospitalmar.Model.MailSender.MailSender
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.ViewModel
import com.reinosa.hospitalmar.ui.theme.blueproject
import com.reinosa.hospitalmar.widgets.Drawer.DrawerHeader
import com.reinosa.hospitalmar.widgets.Drawer.DrawerItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate





@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EvalContainer(navController: NavController ,viewModel: ViewModel){

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.Evaluar)) },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                    }
                },
                backgroundColor = blueproject.copy(alpha = 0.8f),
                actions = {
                    val context = LocalContext.current
                    IconButton(onClick = {

                        val notaCalculada = (viewModel.notaFinal/16)*10
                        val informe = Informe(
                            idInforme= null,
                            idAlumno= viewModel.alumnoSelected!!.idAlumno,
                            idModulo= viewModel.moduloSelected!!.idModulo,
                            idCompetencia= viewModel.competenciaSelected!!.idCompetencia,
                            fechaGeneracion= LocalDate.now().toString(),
                            notaFinal= notaCalculada
                        )
                        CoroutineScope(Dispatchers.Main).launch {
                            val informeJob = async(Dispatchers.IO) {
                                viewModel.postInforme(informe)
                            }
                            informeJob.await()

                            val idInformeJob = async(Dispatchers.IO) {
                                viewModel.getUltimoIdInforme()
                            }
                            idInformeJob.await()

                            // Después de que se complete la inserción del informe, obtén el último ID del informe
                            val idInforme = viewModel.ultimoIdInforme.value ?: -1


                            //Continúa con el resto del código utilizando el ID del informe obtenido
                            viewModel.competenciaSelected?.let { competencia ->
                                for (i in 1..4) {
                                    val nota = Nota(
                                        idNota = null,
                                        idInforme = idInforme,
                                        nota = viewModel.listNotas[i - 1],
                                        comentario = viewModel.listComentarios[i - 1].toString(),
                                        orden = i
                                    )
                                    viewModel.postInformeNota(nota)
                                }
                            }
                            // Resto del código después de completar la inserción de informe y notas
                        }
                        //Aqui se guardará informe y pasara a informe screen
                        Toast.makeText(context, "Informe guardat", Toast.LENGTH_SHORT).show()
                        viewModel.notaFinal = 0
                        //Nav
                        navController.navigate("home")
                        val mailsender = MailSender()
                        mailsender.sendEmail("gery13598@gmail.com",
                            "Nou informe",
                            " S'ha generat un nou informe. Entra a la app per comprovar-ho")
                    }) {
                        Icon(Icons.Filled.Save, contentDescription = "Localized description")
                    }
                },
            )},

        drawerContent = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                DrawerHeader(viewModel)
                DrawerItems(navController = navController)

            }
        },
        drawerBackgroundColor = Color.White // Cambiar por el color deseado
    ){
        EvaluarScreen(navController = navController, viewModel)
    }
}








