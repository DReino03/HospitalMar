package com.reinosa.hospitalmar.ViewModel

import android.os.Looper
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reinosa.hospitalmar.Model.ApiInterface.Repository
import com.reinosa.hospitalmar.Model.DataClass.Alumno
import com.reinosa.hospitalmar.Model.DataClass.Competencia
import com.reinosa.hospitalmar.Model.DataClass.Modulo
import com.reinosa.hospitalmar.Model.DataClass.Profesor
import com.reinosa.hospitalmar.Model.Informe.InformeData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest

class LoginViewModel(): ViewModel() {
    var isChecked by mutableStateOf(false)
    var currentAlumno = MutableLiveData<Alumno>()
    var currentProfesor= MutableLiveData<Profesor>()
    val success = MutableLiveData<Boolean>()
    var studentsSelected = mutableStateOf(listOf<String>())
    val modulList = MutableLiveData<List<Modulo>?>()
    val competenciaList = MutableLiveData<List<Competencia>>()
    val alumnosPorIdProfesor = MutableLiveData<List<Alumno>?>()
    var repository= MutableLiveData<Repository>()
    var alumnoSelected: Alumno? = null
    var moduloSelected: Modulo? = null
    var competenciaSelected: Competencia? = null
    var isAlumno: Boolean = false


    fun getMd5DigestForPassword(password: String): String {
        val digest = MessageDigest.getInstance("MD5").digest(password.toByteArray(Charsets.UTF_8))
        return digest.joinToString("") { "%02x".format(it) }
    }

    suspend fun getAlumno(identificador: String) {
            try {
                val response = repository.value?.getAlumno("/alumno/$identificador")

                if (response?.isSuccessful == true) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        currentAlumno.value = response.body()
                    } else {
                        withContext(Dispatchers.Main) {
                            currentAlumno.value = response.body()
                        }
                    }
                    Log.d("ALUMNO", "${currentAlumno.value}")
                } else {
                    if (response != null) {
                        Log.e("Error :", response.message())
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "Excepción en la corrutina: ${e.message}", e)
            }
    }
    suspend fun getProfesor(identificador: String) {
        try {
            val response = repository.value?.getProfesor("/profesor/$identificador")

            if (response?.isSuccessful == true) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    currentProfesor.value = response.body()
                } else {
                    withContext(Dispatchers.Main) {
                        currentProfesor.value = response.body()
                    }
                }
                Log.d("Usuario en el get", currentProfesor.value.toString())
                success.postValue(true)
            } else {
                if (response != null) {
                    Log.e("Error :", response.message())
                }
            }
        } catch (e: Exception) {
            Log.e("Error", "Excepción en la corrutina: ${e.message}", e)
        }
    }


    suspend fun selectAllCompetencias () {
            val response = repository.value?.selectAllCompetencias("/competencia")
            withContext(Dispatchers.Main) {
                if (response?.isSuccessful == true){
                    competenciaList.postValue(response.body())
                }
                else{
                    if (response != null) {
                        Log.e("Error:", response.message())
                    }
                }
        }
    }
    fun getAlumnosIdProfesor() {
        success.postValue(false)
        val currentProf = currentProfesor.value
        if (currentProf != null) {
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("COMPROBACION", currentProf.toString())
                val response = repository.value?.selectAlumnosPorProfesor(currentProf.idPorfesor)
                if (response != null) {
                    withContext(Dispatchers.Main) {
                        if (response?.isSuccessful == true) {
                            alumnosPorIdProfesor.postValue(response.body())
                        } else {
                            Log.e("Error:", response.message())
                        }
                    }
                }
                success.postValue(true)
            }
        } else {
            Log.e("Error:", "currentProfesor.value es null")
        }
    }

    suspend fun selectModuloPorCiclo(etiqueta: String){
            val response = repository.value?.selectModuloPorCiclo("/modulo/$etiqueta")
            withContext(Dispatchers.Main) {
                if (response?.isSuccessful == true){
                    modulList.postValue(response.body())
                }
                else{
                    if (response != null) {
                        Log.e("Error:", response.message())
                    }
                }
            }

    }

    fun updatePasswordAlumno (contrasenya: String){
        val idAlumno = currentAlumno.value!!.idAlumno
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Llama a la función de la interfaz Retrofit para actualizar el idUsuario
                repository.value?.updatePasswordAlumno(idAlumno, contrasenya)
            } catch (e: Exception) {
                Log.d("TRY CATCH FUNCION", "${e.message}")
            }
        }
    }
    fun updatePasswordProfesor (contrasenya: String){
        val idProfesor = currentProfesor.value!!.idPorfesor
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Llama a la función de la interfaz Retrofit para actualizar el idUsuario
                repository.value?.updatePasswordProfesor(idProfesor, contrasenya)
            } catch (e: Exception) {
                Log.d("TRY CATCH FUNCION", "${e.message}")
            }
        }
    }

    fun setSelectedAlumno (alumno: Alumno) {
        alumnoSelected = alumno
    }

    fun setSelectedModulo (modulo: Modulo){
        moduloSelected = modulo
    }
    fun setSelectedCompetencia (competencia: Competencia){
        competenciaSelected = competencia
    }






}