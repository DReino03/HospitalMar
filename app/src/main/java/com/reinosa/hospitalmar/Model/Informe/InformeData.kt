package com.reinosa.hospitalmar.Model.Informe

import androidx.compose.runtime.MutableState

data class InformeData(
    val modul: String,
    val persona: MutableState<List<String>>,
    val rating: List<Pair<String, Int>>,
    val observaciones: List<String>,
)