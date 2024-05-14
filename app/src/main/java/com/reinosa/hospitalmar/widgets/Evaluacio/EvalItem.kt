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
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
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
import androidx.navigation.NavController
import com.reinosa.hospitalmar.Model.DataClass.EvalCard
import com.reinosa.hospitalmar.Model.Informe.InformeData
import com.reinosa.hospitalmar.ViewModel.LoginViewModel

@Composable
fun EvalItem(text: String, index: Int, comments: MutableList<MutableList<String>>) {
    val selectedCardIndex = remember { mutableStateOf(-1) }
    val comment = remember { mutableStateOf("") }
    val evalCard = EvalCard(text)


    Card(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .clickable { selectedCardIndex.value = index },
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Row() {
                androidx.compose.material3.Text(
                    text = text,
                    Modifier
                        .padding(16.dp),
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
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
                comments[index].forEachIndexed { commentIndex, commentText ->
                    Row {
                        androidx.compose.material3.Text(commentText, modifier = Modifier.padding(16.dp), style = LocalTextStyle.current.copy(color = Color.Gray))
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { comments[index].removeAt(commentIndex) }) {
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
                        label = { androidx.compose.material3.Text("Comment") },

                        )
                    if (comment.value.isNotEmpty() && comment.value !in comments[index]) {
                        IconButton(onClick = {
                            comments[index].add(comment.value)
                            comment.value = ""
                        }) {
                            Icon(Icons.Filled.Send, contentDescription = "Send", tint = Color.White)
                        }
                    }
                }

            }
        }
    }
}
