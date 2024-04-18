package com.reinosa.hospitalmar.Model.ApiInterface

class Repository(correo: String, contrasenya: String) {
    val apiInterface = ApiInterface.create(correo, contrasenya)

    suspend fun getAlumnos() = apiInterface.getAlumnos()
    suspend fun getProfesores() = apiInterface.getProfesores()
}