package com.reinosa.hospitalmar.widgets.Evaluacio

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DataClass.EvalCard
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.widgets.Competencias.CompetenciasItem
import com.reinosa.hospitalmar.widgets.Drawer.DrawerHeader
import com.reinosa.hospitalmar.widgets.Drawer.DrawerItems
import com.reinosa.hospitalmar.widgets.Evaluacio.EvalItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EvaluarScreen(navController: NavController, viewModel: LoginViewModel) {
    val ratings: MutableList<Pair<String, Int>> = mutableListOf()
    val comments = remember { mutableStateListOf<MutableList<String>>() }

    repeat(5) {
        comments.add(mutableListOf())
    }


    Column {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.padding(12.dp))
                Text(viewModel.competenciaSelected!!.nombreCompetencia,
                    style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )
                androidx.compose.material3.Text(
                    text = viewModel.competenciaSelected!!.descripcion,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            viewModel.competenciaSelected?.let { competencia ->
                items(4) { index ->
                    val pregunta = when (index) {
                        0 -> competencia.pregunta1
                        1 -> competencia.pregunta2
                        2 -> competencia.pregunta3
                        3 -> competencia.pregunta4
                        else -> ""
                    }
                    EvalItem(text = pregunta, index, comments)
                }
            }

            // Añade las calificaciones a la lista
            viewModel.competenciaSelected?.descripcion?.let { descripcion ->
                repeat(5) {
                    val data = Pair(descripcion, 0)
                    ratings.add(data)
                }
            }
        }
    }
}