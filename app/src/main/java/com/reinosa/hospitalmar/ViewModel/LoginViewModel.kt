package com.reinosa.hospitalmar.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.reinosa.hospitalmar.Model.Credentials.checkCredentials
import java.security.MessageDigest

class LoginViewModel(): ViewModel() {
    var isChecked by mutableStateOf(false)
    fun login(email: String, password: String): Boolean {
        val hashedPassword = hashPassword(password)
        return checkCredentials(email, hashedPassword)
    }
    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(bytes)
        return hashedBytes.joinToString("") { "%02x".format(it) }
    }


}
