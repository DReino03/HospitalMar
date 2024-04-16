package com.reinosa.hospitalmar.Model.Credentials

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.reinosa.hospitalmar.MainActivity

fun checkCredentials(creds: Credentials, context: Context): Boolean {
    if (creds.isNotEmpty() && creds.login == "admin") {
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as Activity).finish()
        return true
    } else {
        Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
        return false
    }
}