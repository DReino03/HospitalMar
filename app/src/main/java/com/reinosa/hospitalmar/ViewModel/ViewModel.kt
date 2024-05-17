package com.reinosa.hospitalmar.ViewModel

import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reinosa.hospitalmar.Model.ApiInterface.Repository
import com.reinosa.hospitalmar.Model.DataClass.Alumno
import com.reinosa.hospitalmar.Model.DataClass.Competencia
import com.reinosa.hospitalmar.Model.DataClass.Informe
import com.reinosa.hospitalmar.Model.DataClass.Modulo
import com.reinosa.hospitalmar.Model.DataClass.Nota
import com.reinosa.hospitalmar.Model.DataClass.Profesor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest

class ViewModel(): ViewModel() {
    var isChecked by mutableStateOf(false)
    var currentAlumno = MutableLiveData<Alumno>()
    var currentProfesor= MutableLiveData<Profesor>()
    val success = MutableLiveData<Boolean>()
    var studentsSelected = mutableStateOf(listOf<String>())
    val modulList = MutableLiveData<List<Modulo>?>()
    val informeList = MutableLiveData<List<Informe>>()
    val notasList = MutableLiveData<List<Nota>>()
    val competenciaList = MutableLiveData<List<Competencia>>()
    val alumnosPorIdProfesor = MutableLiveData<List<Alumno>?>()
    var repository= MutableLiveData<Repository>()
    var alumnoSelected: Alumno? = null
    var moduloSelected: Modulo? = null
    var competenciaSelected: Competencia? = null
    var informeSelected: Informe? = null
    var isAlumno: Boolean = false
    var comeFromInforme: Boolean = false
    var notaFinal = 0
     var listNotas = mutableListOf<Int>()
    lateinit var listComentarios: MutableList<MutableList<String>>
    var ultimoIdInforme = MutableLiveData<Int>()


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
    suspend fun getInforme(idAlumno: Int, idModulo: Int, idCompetencia: Int) {
        try {
            val response = repository.value?.getInforme("/informe/$idAlumno/$idModulo/$idCompetencia")
            if (response?.isSuccessful == true){
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    informeList.value = response.body()
                } else {
                    withContext(Dispatchers.Main) {
                        informeList.value = response.body()
                    }
                }
            } else{
                if (response != null) {
                    Log.e("Error :", response.message())
                }
            }
        } catch (e: Exception){
            Log.e("No hay informe", "Excepción en la corrutina: ${e.message}", e)
        }
    }
    suspend fun getNotas(idInforme: Int) {
        try {
            val response = repository.value?.getNota("/notas/$idInforme")
            if (response?.isSuccessful == true){
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    notasList.value = response.body()
                } else {
                    withContext(Dispatchers.Main) {
                        notasList.value = response.body()
                    }
                }
            } else{
                if (response != null) {
                    Log.e("Error :", response.message())
                }
            }
        } catch (e: Exception){
            Log.e("No hay informe", "Excepción en la corrutina: ${e.message}", e)
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

    suspend fun getUltimoIdInforme() {
            val response = repository.value?.selectLastIdInforme()
            if (response != null) {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        ultimoIdInforme.postValue(response.body())
                    } else {
                        Log.e("Error:", response.message())
                    }
                }
            }
    }


    suspend fun postInforme(informe: Informe){
        withContext(Dispatchers.IO) {
            repository.value?.insertInforme(informe)
        }
    }

    fun postInformeNota(nota: Nota){
        CoroutineScope(Dispatchers.IO).launch {
            repository.value?.insertInformeNota(nota)
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
    fun setSelectedInforme (informe: Informe){
        informeSelected = informe
    }






}