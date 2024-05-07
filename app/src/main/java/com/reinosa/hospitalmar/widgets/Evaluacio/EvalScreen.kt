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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DataClass.EvalCard
import com.reinosa.hospitalmar.R
import com.reinosa.hospitalmar.widgets.Drawer.DrawerHeader
import com.reinosa.hospitalmar.widgets.Drawer.DrawerItems
import kotlinx.coroutines.launch
import com.reinosa.hospitalmar.widgets.Evaluacio.StarMenu
@Composable
fun EvalItem(modul: String) {
    val ratings: MutableList<Pair<String, Int>> = mutableListOf()
    val text = "stringResource(R.string.eval_text)"
    val comment = remember { mutableStateOf("") }
    val comments = remember { mutableStateListOf<String>() }
    val selectedCardIndex = remember { mutableStateOf(-1) }

    Column {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.padding(12.dp))
                Text("Iniciativa", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))
            }

            items(5) {index->
                val evalCard = EvalCard(text)
                val selectedStar = remember { mutableStateOf(0) }
                Card(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                        .clickable { selectedCardIndex.value = index },
                ) {
                    Column {
                        Spacer(modifier = Modifier.padding(8.dp))
                        Row() {
                            Text(
                                text = text,
                                Modifier.padding(16.dp))
                            IconButton(onClick = { selectedCardIndex.value = index }) {
                                Icon(Icons.Filled.Comment, contentDescription = "Comment")
                            }
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Row {
                            StarMenu(4, evalCard)
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        if (selectedCardIndex.value == index) {
                            comments.forEachIndexed { commentIndex, commentText ->
                                Row {
                                    Text(commentText, modifier = Modifier.padding(16.dp), style = LocalTextStyle.current.copy(color = Color.Gray))
                                    Spacer(modifier = Modifier.weight(1f))
                                    IconButton(onClick = { comments.removeAt(commentIndex) }) {
                                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                                    }
                                }
                            }
                            Row(Modifier.fillMaxWidth()) {
                                TextField(
                                    modifier = Modifier.weight(1f),
                                    value = comment.value,
                                    onValueChange = { comment.value = it },
                                    label = { Text("Comment") }
                                )
                                if (comment.value.isNotEmpty()) {
                                    IconButton(onClick = {
                                        comments.add(comment.value)
                                        comment.value = ""
                                    }) {
                                        Icon(Icons.Filled.Send, contentDescription = "Send")
                                    }
                                }
                            }

                        }
                    }
                }
                var data = Pair(text, selectedStar.value)
                ratings.add(index,data)
            }
        }
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EvalScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val modul : String = remember {
        navController.previousBackStackEntry!!.arguments?.getString("modul") ?: "No proporcionado"
    }
    val persona: List<String>? = remember {
        navController.previousBackStackEntry?.arguments?.getStringArrayList("persona")?.toList()
    }
    val rating: List<Pair<String, Int>>? = remember {
        navController.previousBackStackEntry?.arguments?.getString("rating")?.split(",")
            ?.map { it.split(":").let { pair -> Pair(pair[0], pair[1].toInt()) } }
    }
    Log.e("EvalScreen", modul)
    data class InformeData(
        val modul: String,
        val persona: List<String>,
        val rating: List<Pair<String, Int>>,
        // Agrega aquí cualquier otro dato que necesites recolectar
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    androidx.compose.material.Text(
                        text = stringResource(R.string.drawer_graphics),
                        textAlign = TextAlign.Justify)
                        },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Localized description")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val toast = Toast(context)
                        toast.duration = Toast.LENGTH_SHORT
                        toast.setText("Guardado Correctamente")
                        toast.show()
                      //  navController.navigate("result/${InformeData}")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        drawerContent = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                DrawerHeader()
                DrawerItems(navController = navController)

            }
        },
        drawerBackgroundColor = Color.White // Cambiar por el color deseado
    ){
        EvalItem(modul)
    }

}