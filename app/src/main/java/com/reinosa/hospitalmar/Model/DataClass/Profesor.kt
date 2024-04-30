package com.reinosa.hospitalmar.Model.DataClass

data class Profesor (
    val idPorfesor: Int,
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val identificador: String,
    val etiqueta: String,
    val categoria: String,
    val grupos: String,
    val contrasenya: String,
    val tutor: Boolean,
    val admin: Boolean
)