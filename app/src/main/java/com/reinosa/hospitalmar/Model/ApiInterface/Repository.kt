package com.reinosa.hospitalmar.Model.ApiInterface

import com.reinosa.hospitalmar.Model.DataClass.Alumno
import java.net.URL

class Repository(correo: String, contrasenya: String) {
    val apiInterface = ApiInterface.create(correo, contrasenya)

    suspend fun getAlumnos(url: String) = apiInterface.getAlumnos(url)
    suspend fun getAlumno(url: String) = apiInterface.getAlumno(url)

    suspend fun login(alumno: Alumno) = apiInterface.login(alumno)
    suspend fun getModulos(url: String) = apiInterface.getModulos(url)
}