package com.reinosa.hospitalmar.widgets.Evaluacio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.reinosa.hospitalmar.Model.DataClass.EvalCard
import com.reinosa.hospitalmar.ViewModel.LoginViewModel

@Composable
fun   EvalItem(loginViewModel: LoginViewModel) {
    val ratings: MutableList<Pair<String, Int>> = mutableListOf()
    val text = "stringResource(R.string.eval_text)"
    val comment = remember { mutableStateOf("") }
    val comments = remember { mutableStateListOf<String>() }
    val selectedCardIndex = remember { mutableStateOf(-1) }

    // Crea una nueva instancia de InformeData
    val informeData = LoginViewModel.InformeData(
        modul = "Modul",
        persona = loginViewModel.studentsSelected,
        rating = ratings,
        observaciones = comments
    )

    // Añade la nueva instancia de InformeData a la lista de InformeData en LoginViewModel
    loginViewModel.updateInformeDataList(listOf(informeData))


    Column {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.padding(12.dp))
                Text("Iniciativa", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))
            }

            items(5) {index->
                val evalCard = EvalCard(text)
                val selectedStar = remember { mutableIntStateOf(0) }
                Card(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                        .clickable { selectedCardIndex.value = index },
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(8.dp)
                    ) {
                        Spacer(modifier = Modifier.padding(8.dp))
                        Row() {
                            Text(
                                text = text,
                                Modifier
                                    .padding(16.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.weight(0.6f))
                            IconButton(onClick = { selectedCardIndex.value = index }) {
                                Icon(Icons.Filled.Comment, contentDescription = "Comment", tint = Color.White)
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
                                    modifier = Modifier
                                        .weight(1f)
                                        .navigationBarsPadding(),
                                    value = comment.value,
                                    onValueChange = { comment.value = it },
                                    label = { Text("Comment") },

                                )
                                if (comment.value.isNotEmpty() && comment.value !in comments) {
                                    IconButton(onClick = {
                                        comments.add(comment.value)
                                        comment.value = ""
                                    }) {
                                        Icon(Icons.Filled.Send, contentDescription = "Send", tint = Color.White)
                                    }
                                }
                            }

                        }
                    }
                }
                val data = Pair(text, selectedStar.intValue)
                ratings.add(index,data)
            }
        }
    }
}
