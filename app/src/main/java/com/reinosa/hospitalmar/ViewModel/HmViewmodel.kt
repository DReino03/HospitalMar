package com.reinosa.hospitalmar.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reinosa.hospitalmar.Model.ApiInterface.Repository
import com.reinosa.hospitalmar.Model.DataClass.Modulo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HmViewmodel (): ViewModel(){

    val success = MutableLiveData<Boolean>()
    var repository: Repository

    init {
        // Aquí podrías inicializar repository
        repository = Repository(contrasenya = "", correo = "")
    }

}
