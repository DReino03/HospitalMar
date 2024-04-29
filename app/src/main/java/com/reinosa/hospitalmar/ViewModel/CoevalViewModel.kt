package com.reinosa.hospitalmar.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.reinosa.hospitalmar.Model.DataClass.Alumno

class CoevalViewModel : ViewModel(){

    var studentsSelected = mutableStateOf(listOf<String>())


}