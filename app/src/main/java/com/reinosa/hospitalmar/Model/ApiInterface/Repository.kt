package com.reinosa.hospitalmar.Model.ApiInterface

import com.reinosa.hospitalmar.Model.DataClass.Alumno
import com.reinosa.hospitalmar.Model.DataClass.Profesor
import retrofit2.http.Url

class Repository(correo: String, contrasenya: String) {
    val apiInterface = ApiInterface.create(correo, contrasenya)

    suspend fun getAlumnos(url: String) = apiInterface.getAlumnos(url)
    suspend fun getAlumno(url: String) = apiInterface.getAlumno(url)
    suspend fun getProfesor(url: String) = apiInterface.getProfesor(url)
    suspend fun loginAlumno(alumno: Alumno) = apiInterface.loginAlumno(alumno)
    suspend fun loginProfesor(profesor: Profesor) = apiInterface.loginProfesor(profesor)
    suspend fun getModulos(url: String) = apiInterface.getModulos(url)
    suspend fun selectAlumnosPorProfesor(idProfesor: Int) = apiInterface.selectAlumnosPorProfesor(idProfesor)
}