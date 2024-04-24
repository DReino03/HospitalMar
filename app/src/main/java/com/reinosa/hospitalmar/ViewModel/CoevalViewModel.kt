package com.reinosa.hospitalmar.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CoevalViewModel : ViewModel(){

    var studentsSelected by mutableStateOf(mutableListOf<String>())



}