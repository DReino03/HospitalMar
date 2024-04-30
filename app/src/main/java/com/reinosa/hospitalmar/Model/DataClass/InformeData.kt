package com.reinosa.hospitalmar.Model.DataClass

data class InformeData(
    val modul: String,
    val persona: List<String>,
    val rating: List<Pair<String, Int>>,
    // Agrega aquí cualquier otro dato que necesites recolectar
)