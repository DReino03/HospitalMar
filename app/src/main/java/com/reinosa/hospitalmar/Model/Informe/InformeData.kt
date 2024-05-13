package com.reinosa.hospitalmar.Model.Informe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList

data class InformeData(
    val modul: String,
    val persona: MutableState<List<String>>,
    val rating: List<Pair<String, Int>>,
    val observaciones: SnapshotStateList<MutableList<String>>,
)