package com.reinosa.hospitalmar.Model.ApiInterface

import com.google.gson.GsonBuilder
import com.reinosa.hospitalmar.Model.DataClass.Alumno
import com.reinosa.hospitalmar.Model.DataClass.Modulo
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
import retrofit2.http.Url

interface ApiInterface {
    @GET
    suspend fun getAlumnos(@Url url: String): Response<Alumno>

    @GET
    suspend fun getAlumno(@Url url: String): Response<Alumno>
    @GET
    suspend fun getProfesor(@Url url : String): Response<Profesor>

    @GET
    suspend fun getModulos (@Url url: String): Response<List<Modulo>>

    @POST("user/login/alumno")
    suspend fun loginAlumno(@Body alumno: Alumno): Response<ResponseBody>
    @POST("user/login/profesor")
    suspend fun loginProfesor(@Body profesor: Profesor): Response<ResponseBody>


    companion object{
        val BASE_URL = "http://172.23.6.122:8080/"
        //url pc alex: 192.168.1.104
        //url pc jordi: 192.168.56.1
        //url pc clase alex: 172.23.6.123:8080
        //url api aws 34.192.34.148:8081
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