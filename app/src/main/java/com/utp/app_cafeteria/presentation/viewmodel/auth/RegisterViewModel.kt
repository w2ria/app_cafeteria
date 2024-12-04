package com.utp.app_cafeteria.presentation.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    // LiveData para errores en el correo y la contraseña
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError

    // LiveData para el estado del registro
    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> get() = _registerSuccess

    // Validar el correo
    private fun validateEmail(email: String): Boolean {
        val utpDomainPattern = "^[A-Za-z0-9._%+-]+@utp\\.edu\\.pe$".toRegex()
        return if (utpDomainPattern.matches(email)) {
            _emailError.value = null // Sin errores
            true
        } else {
            _emailError.value = "El correo debe pertenecer al dominio @utp.edu.pe"
            false
        }
    }

    // Validar la contraseña
    private fun validatePassword(password: String): Boolean {
        return if (password.length >= 8) {
            _passwordError.value = null // Sin errores
            true
        } else {
            _passwordError.value = "La contraseña debe tener al menos 8 caracteres"
            false
        }
    }

    // Asignar el rol según la primera letra del correo
    private fun assignRole(email: String): String {
        return when {
            email.startsWith("u", true) -> "Estudiante"
            email.startsWith("c", true) -> "Profesor"
            else -> "Desconocido"
        }
    }

    // Registrar el cliente
    fun registerClient(name: String, email: String, password: String, phone: String) {
        if (validateEmail(email) && validatePassword(password)) {
            val role = assignRole(email)

            // Aquí puedes guardar en la base de datos (supabase) el cliente
            // Crear un objeto cliente y guardarlo en la base de datos
            // Ejemplo con Supabase (asegúrate de configurar el SDK de Supabase en tu proyecto)
            val client = Client(name, email, password, phone, role)

            // Supabase call to save the client
            // supabaseClient.from("clients").insert(client)

            _registerSuccess.value = true // Suponiendo que la inserción fue exitosa
        } else {
            _registerSuccess.value = false
        }
    }
}

// Modelo de datos para el cliente
data class Client(
    val nombre: String,
    val correo: String,
    val contrasenia: String,
    val telefono: String,
    val rol: String
)
