package com.reinosa.hospitalmar.Model.DataClass

import java.util.Date

data class Informe(
    val idInforme: Int,
    val idAlumno: Int,
    val fechaGeneracion: Date,
    val idAutoEvaluacion: Int,
    val idCoevaluacion: Int,
    val idEvaluacionProfesor: Int,
    val puntuacionAutoEva: Int,
    val puntuacionCoEva: Int,
    val puntuacionEvaProf: Int,
    val notaFinal: Int,
    val puntuacion: Int
)