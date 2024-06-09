package com.reinosa.hospitalmar.Model.ApiInterface

import com.google.gson.GsonBuilder
import com.reinosa.hospitalmar.Model.DataClass.Alumno
import com.reinosa.hospitalmar.Model.DataClass.Competencia
import com.reinosa.hospitalmar.Model.DataClass.Informe
import com.reinosa.hospitalmar.Model.DataClass.Modulo
import com.reinosa.hospitalmar.Model.DataClass.Nota
import com.reinosa.hospitalmar.Model.DataClass.Profesor
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiInterface {
    @GET
    suspend fun getAlumnos(@Url url: String): Response<Alumno>

    @GET
    suspend fun getAlumno(@Url url: String): Response<Alumno>
    @GET
    suspend fun getInforme(@Url url: String): Response<List<Informe>>
    @GET ("informe/notas/{idinforme}")
    suspend fun getNotas (@Path("idinforme") idinforme: Int): Response<List<Nota>>
    @GET
    suspend fun getProfesor(@Url url : String): Response<Profesor>

    @GET
    suspend fun getModulos (@Url url: String): Response<List<Modulo>>
    @GET
    suspend fun getAllCompetencias (@Url url: String): Response<List<Competencia>>

    @GET ("alumno/profesor/{idProfesor}/alumnos")
    suspend fun selectAlumnosPorProfesor (@Path("idProfesor") idProfesor: Int): Response<List<Alumno>>

    @GET
    suspend fun selectModuloPorCiclo(@Url url: String): Response<List<Modulo>>
    @GET ("informe/ultimoIdInforme")
    suspend fun selectLastIdInforme(): Response<Int>
    @POST("user/login/alumno")
    suspend fun loginAlumno(@Body alumno: Alumno): Response<ResponseBody>
    @POST("user/login/profesor")
    suspend fun loginProfesor(@Body profesor: Profesor): Response<ResponseBody>
    @PUT ("/alumno/update/contrasenya/{idAlumno}/{contrasenya}")
    suspend fun updatePasswordAlumno(@Path("idAlumno") idAlumno: Int, @Path("contrasenya") contrasenya: String): Response<ResponseBody>
    @PUT ("/profesor/update/contrasenya/{idProfesor}/{contrasenya}")
    suspend fun updatePasswordProfesor(@Path("idProfesor") idAlumno: Int, @Path("contrasenya") contrasenya: String): Response<ResponseBody>
    @POST ("/informe/insertar")
    suspend fun insertInforme(@Body informe: Informe): Response<ResponseBody>
    @POST ("/informe/insertar/nota")
    suspend fun insertInformeNota(@Body nota: Nota): Response<ResponseBody>

    companion object{
        val BASE_URL = "http://192.168.56.1:8080/"
        fun create(username: String, password: String): ApiInterface {
            val credentials = Credentials.basic(username, password)

            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .header("Authorization", credentials)
                        .build()
                    chain.proceed(request)
                }
                .build()

            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }


    }
}