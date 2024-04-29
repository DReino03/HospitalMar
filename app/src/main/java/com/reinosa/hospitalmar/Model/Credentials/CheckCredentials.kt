package com.reinosa.hospitalmar.Model.Credentials

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.reinosa.hospitalmar.MainActivity

fun checkCredentials(creds: String, context: String): Boolean {
    if (creds == "correoalumno1@exaple.com" && context == "contrasena1") {
        return true
    }else{
        return false
    }
}