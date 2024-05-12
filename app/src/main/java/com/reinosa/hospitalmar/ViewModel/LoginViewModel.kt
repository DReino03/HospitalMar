package com.reinosa.hospitalmar.ViewModel

import android.os.Looper
import android.util.Log
import androidx.compose.runtime.MutableState
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
    var studentsSelected = mutableStateOf(listOf<String>())
    val modulList = MutableLiveData<List<Modulo>?>()
    val alumnosPorIdProfesor = MutableLiveData<List<Alumno>?>()
    var repository: Repository

    init {
        // Aquí podrías inicializar repository
        repository = Repository(contrasenya = "", correo = "")
    }
    fun getMd5DigestForPassword(password: String): String {
        val digest = MessageDigest.getInstance("MD5").digest(password.toByteArray(Charsets.UTF_8))
        return digest.joinToString("") { "%02x".format(it) }
    }

    fun getAlumno(identificador: String) {
        Log.d("ENTRAAA?", "SOD")
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
                    Log.d("ALUMNO", "${currentAlumno.value}")
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
            val response = repository.getModulos("/modulo")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful){
                    modulList.postValue(response.body())
                }
                else{
                    Log.e("Error:", response.message())
                }
            }
            success.postValue(true)
        }
    }
    fun getAlumnosIdProfesor() {
        success.postValue(false)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("COMPROBACION", currentProfesor.value.toString())
            val response = repository.selectAlumnosPorProfesor(currentProfesor.value!!.idPorfesor)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful){
                    alumnosPorIdProfesor.postValue(response.body())
                }
                else{
                    Log.e("Error:", response.message())
                }
            }
            success.postValue(true)
        }
    }

    data class InformeData(
        val modul: String,
        val persona: MutableState<List<String>>,
        val rating: List<Pair<String, Int>>,
        val observaciones: List<String>,
    )
    // Agrega una nueva variable de estado mutable para almacenar los datos de InformeData
    var informeDataList = MutableLiveData<List<InformeData>>()

    // Agrega una función para actualizar los datos de InformeData
    fun updateInformeDataList(data: List<InformeData>) {
        informeDataList.value = data
    }




}