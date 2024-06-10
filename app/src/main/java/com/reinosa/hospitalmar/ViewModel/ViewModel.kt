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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
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
    val competenciaSelectedText = mutableStateOf("Seleccionar competencia")
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
    ////SEARCHBAR////////

    //Saber si se esta buscando o no (search bar)
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    private val _studentsList = MutableStateFlow(listOf<Alumno>())
    val studentsList: StateFlow<List<Alumno>> = _studentsList

    private val _filteredStudentsList = MutableStateFlow(listOf<Alumno>())
    val filteredStudentsList: StateFlow<List<Alumno>> = _filteredStudentsList

    fun onSearchTextChange(newText: String) {
        _searchText.value = newText
        _filteredStudentsList.value = getFilteredStudents(newText)
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
    }

    private fun getFilteredStudents(query: String): List<Alumno> {
        return if (query.isEmpty()) {
            _studentsList.value
        } else {
            _studentsList.value.filter {
                it.nombre.contains(query, ignoreCase = true)
            }
        }
    }

////DROP DOWN COMPETENCIAS////////
// Lista de competencias
private val _competenciaList = MutableStateFlow(listOf<Competencia>())
    val competenciaListt: StateFlow<List<Competencia>> = _competenciaList

    // Competencia seleccionada
    private val _competenciaSelectedd = MutableStateFlow<Competencia?>(null)
    val competenciaSelectedd: StateFlow<Competencia?> = _competenciaSelectedd



    // Elimina la parte duplicada de la lista de competencias
    val selectedCompetencia = MutableStateFlow<Competencia?>(null)

    // Elimina el método setSelectedCompetenciaa
    fun setSelectedCompetenciaa(competencia: Competencia) {
        selectedCompetencia.value = competencia
    }


    private val _displayText = MutableStateFlow("")
    val displayText: StateFlow<String> = _displayText

    // Función para actualizar el texto
    fun updateText(newText: String?) {
        if (newText != null) {
            _displayText.value = newText
        }
    }
    private val _competenciaInfo = MutableStateFlow(Competencia(1, "", "", "", "", "", ""))
    val competenciaInfo: StateFlow<Competencia> = _competenciaInfo

    // Función para actualizar el UserInfo
    fun updateCompetenciaInfo(newCompetencia: Competencia?) {
        if (newCompetencia != null) {
            _competenciaInfo.value = newCompetencia
        }
    }
}




