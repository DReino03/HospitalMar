package com.reinosa.hospitalmar.ViewModel

import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reinosa.hospitalmar.Model.ApiInterface.Repository
import com.reinosa.hospitalmar.Model.DataClass.Alumno
import com.reinosa.hospitalmar.Model.DataClass.Modulo
import com.reinosa.hospitalmar.Model.DataClass.Profesor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest

class LoginViewModel(): ViewModel() {
    var isChecked by mutableStateOf(false)
    var currentAlumno = MutableLiveData<Alumno>()
    var currentProfesor = MutableLiveData<Profesor>()
    val success = MutableLiveData<Boolean>()
    var modulos = MutableLiveData<List<Modulo>>()


    lateinit var repository: Repository

    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(bytes)
        return hashedBytes.joinToString("") { "%02x".format(it) }
    }

    fun getAlumno(identificador: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getAlumno("/alumno/$identificador")

                if (response.isSuccessful) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        currentAlumno.value = response.body()
                    } else {
                        withContext(Dispatchers.Main) {
                            currentAlumno.value = response.body()
                        }
                    }
                    Log.d("lista", "${currentAlumno.value}")
                } else {
                    Log.e("Error :", response.message())
                }
            } catch (e: Exception) {
                Log.e("Error", "Excepción en la corrutina: ${e.message}", e)
            }
        }
    }
    fun getProfesor(identificador: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getProfesor("/profesor/$identificador")

                if (response.isSuccessful) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        currentProfesor.value = response.body()
                    } else {
                        withContext(Dispatchers.Main) {
                            currentProfesor.value = response.body()
                        }
                    }
                    Log.d("lista", "${currentProfesor.value}")
                } else {
                    Log.e("Error :", response.message())
                }
            } catch (e: Exception) {
                Log.e("Error", "Excepción en la corrutina: ${e.message}", e)
            }
        }
    }


    fun getModulos () {
        success.postValue(false)
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getModulos("/modulos")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful){
                    modulos.postValue(response.body())
                }
                else{
                    Log.e("Error:", response.message())
                }
            }
            success.postValue(true)
        }
    }





}