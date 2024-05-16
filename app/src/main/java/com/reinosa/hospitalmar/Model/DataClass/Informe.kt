package com.reinosa.hospitalmar.Model.DataClass


data class Informe(
    val idInforme: Int?,
    val idAlumno: Int,
    val idModulo: Int,
    val idCompetencia: Int,
    val fechaGeneracion: String,
    val notaFinal: Int
)