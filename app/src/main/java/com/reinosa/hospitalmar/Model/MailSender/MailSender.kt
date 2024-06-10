package com.reinosa.hospitalmar.Model.MailSender


import kotlinx.coroutines.DelicateCoroutinesApi
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import javax.mail.MessagingException


class MailSender {
    @OptIn(DelicateCoroutinesApi::class)
    fun sendEmail(email: String, subject: String, message: String) {
        GlobalScope.launch {
            // Configurar propiedades del servidor SMTP
            val props = Properties().apply {
                put("mail.smtp.auth", "true")
                put("mail.smtp.starttls.enable", "true")
                put("mail.smtp.host", "smtp.gmail.com") // tu servidor SMTP
                put("mail.smtp.port", "587") // Puerto SMTP
            }

            // Configurar la sesión de JavaMail
            val session = Session.getInstance(props, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication("hospitalmar3@gmail.com", "mawi asxz tnix zwwl") //falta encriptar
                }
            })

            try {
                // Crear un objeto MimeMessage
                val mimeMessage = MimeMessage(session).apply {
                    // Establecer dirección de correo electrónico del remitente
                    setFrom(InternetAddress("hospitalmar3@gmail.com"))
                    // Establecer dirección de correo electrónico del destinatario
                    setRecipient(Message.RecipientType.TO, InternetAddress(email))
                    // Establecer el asunto del correo
                    this.subject = subject
                    // Establecer el contenido del correo
                    setText(message)
                }

                // Enviar el correo
                Transport.send(mimeMessage)
            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }
    }
}




