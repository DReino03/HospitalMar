package com.reinosa.hospitalmar.widgets.Evaluacio

import android.annotation.SuppressLint
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.reinosa.hospitalmar.Model.DataClass.EvalCard
import com.reinosa.hospitalmar.ViewModel.ViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun EvalItem(text: String, index: Int, comments: MutableList<MutableList<String>>, viewModel: ViewModel) {
    val selectedCardIndex =  remember{ mutableIntStateOf(-1) }
    val comment = remember { mutableStateOf("") }
    val evalCard = EvalCard(text)

    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { selectedCardIndex.value = index }
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.padding(8.dp))

            Row {
                Text(
                    text = text,
                    Modifier
                        .padding(16.dp)
                        .weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                IconButton(onClick = {
                    if (selectedCardIndex.value == index) {
                        selectedCardIndex.value = -1
                    } else {
                        selectedCardIndex.value = index
                    }
                }) {
                    Icon(Icons.Filled.Comment,
                        contentDescription = "Comment",
                        tint = Color.Gray,
                        modifier = Modifier
                            .weight(0.2f)
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }


            Spacer(modifier = Modifier.padding(8.dp))
            Row {
                StarMenu(4, evalCard, viewModel)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            if (selectedCardIndex.value == index) {
                comments[index].forEachIndexed { commentIndex, commentText ->
                    Row {
                        Text(commentText,
                            modifier = Modifier.padding(16.dp),
                            style = LocalTextStyle.current.copy(color = Color.Gray))
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = {
                                if (commentIndex in comments.indices) {
                                    comments[index].removeAt(commentIndex)
                                    selectedCardIndex.value = -1
                                    selectedCardIndex.value = index
                                }


                            }) {
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
                        label = { Text("Comment") }
                    )
                    if (comment.value.isNotEmpty() && comment.value !in comments[index]) {
                        IconButton(onClick = {
                            comments[index].add(comment.value)
                            comment.value = ""
                        }) {
                            Icon(Icons.Filled.Send, contentDescription = "Send", tint = Color.Gray)
                        }
                    }
                }

            }
        }
    }
}
