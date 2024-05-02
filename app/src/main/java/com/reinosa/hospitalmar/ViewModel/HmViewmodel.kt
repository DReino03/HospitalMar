package com.reinosa.hospitalmar.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reinosa.hospitalmar.Model.ApiInterface.Repository
import com.reinosa.hospitalmar.Model.DataClass.Modulo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HmViewmodel (): ViewModel(){

    var studentsSelected = mutableStateOf(listOf<String>())
    val modulList = MutableLiveData<List<Modulo>?>()
    val success = MutableLiveData<Boolean>()


    lateinit var repository: Repository

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

}