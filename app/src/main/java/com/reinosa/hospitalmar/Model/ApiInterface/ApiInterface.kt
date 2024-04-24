package com.reinosa.hospitalmar.Model.ApiInterface

import com.google.gson.GsonBuilder
import com.reinosa.hospitalmar.Model.DataClass.Alumno
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

    @GET("/login/profesor")
    suspend fun getProfesores(): List<Profesor>

    @POST("user/login/alumno")
    suspend fun login(@Body alumno: Alumno): Response<ResponseBody>

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

