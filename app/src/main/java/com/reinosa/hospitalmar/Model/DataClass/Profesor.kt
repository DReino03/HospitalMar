package com.reinosa.hospitalmar.Model.DataClass


data class Profesor (
    val idPorfesor: Int,
    val contrasenya: String,
    val identificador: Int,
    val correo: String,
    val nombre: String,
    val apellido1: String,
    val apellido2: String,
    val dni: String,
    val admin: Boolean
)