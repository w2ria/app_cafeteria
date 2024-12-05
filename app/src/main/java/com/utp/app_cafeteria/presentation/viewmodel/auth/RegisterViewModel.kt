package com.utp.app_cafeteria.presentation.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _rol = MutableLiveData<String?>()  // Permitir que el rol sea nulo al principio
    val rol: LiveData<String?> get() = _rol

    private val _codigo = MutableLiveData<String?>()
    val codigo: LiveData<String?> get() = _codigo

    private val _isRegistering = MutableLiveData<Boolean>()
    val isRegistering: LiveData<Boolean> get() = _isRegistering

    private val supabaseUrl = "https://tnzshzxssdtgthhbzmwk.supabase.co/rest/v1"
    private val supabaseApiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRuenNoenhzc2R0Z3RoaGJ6bXdrIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTczMjc0MjgzMSwiZXhwIjoyMDQ4MzE4ODMxfQ.UmYtYJ_EOnc7wTnynb60PaEPQRJJtFr9sE_aBQn2Obs"

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    // Validar correo con dominio específico
    private fun validateEmail(correo: String): Boolean {
        val utpDomainPattern = "^[uU][0-9]{8}@utp\\.edu\\.pe$|^[cC][0-9]{5}@utp\\.edu\\.pe$".toRegex()
        return utpDomainPattern.matches(correo)
    }

    // Validar contraseña (al menos 8 caracteres, mayúsculas, minúsculas, números, y un carácter especial)
    private fun validatePassword(contrasenia: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,15}$".toRegex()
        return contrasenia.matches(passwordPattern)
    }

    // Asignar rol basado en la primera letra del correo
    private fun assignRole(correo: String): String? {
        return when {
            correo.startsWith("u", true) -> "Estudiante"
            correo.startsWith("c", true) -> "Profesor"
            else -> null // No acepta otros roles
        }
    }

    // Registrar cliente
    fun registerCliente(
        nombre: String,
        correo: String,
        telefono: String,
        contrasenia: String,
        confiContra: String
    ) {
        // Validaciones de correo y contraseña
        if (!validateEmail(correo)) {
            _message.value = "El correo debe ser del dominio @utp.edu.pe y cumplir con el formato adecuado"
            return
        }

        if (!validatePassword(contrasenia)) {
            _message.value = "La contraseña debe tener entre 8 y 15 caracteres, incluyendo mayúsculas, minúsculas, números y al menos un carácter especial"
            return
        }

        if (contrasenia != confiContra) {
            _message.value = "Las contraseñas no coinciden"
            return
        }

        // Asignar rol automáticamente
        val rolAsignado = assignRole(correo)
        if (rolAsignado == null) {
            _message.value = "El correo debe empezar con 'u' para estudiante o 'c' para profesor"
            return
        }

        _rol.value = rolAsignado // Establece el rol en el LiveData

        // Extraer código del correo y asignarlo al campo correspondiente
        val extractedCode = correo.substringBefore("@")
        _codigo.value = extractedCode // El código extraído no es editable por el usuario

        // Enviar los datos al backend para registrar al cliente
        viewModelScope.launch {
            _isRegistering.value = true  // Indica que se está registrando el cliente

            try {
                val response: HttpResponse = client.post("$supabaseUrl/cliente") {
                    header("apikey", supabaseApiKey)
                    header("Authorization", "Bearer $supabaseApiKey")
                    contentType(ContentType.Application.Json)
                    setBody(
                        mapOf(
                            "nombre" to nombre,
                            "correo" to correo,
                            "codigo" to extractedCode,
                            "rol" to rolAsignado,
                            "telefono" to telefono,
                            "contrasenia" to contrasenia
                        )
                    )
                }

                if (response.status == HttpStatusCode.Created) {
                    _message.value = "Registro exitoso"
                    _isRegistering.value = false  // Desactiva el botón después de un registro exitoso
                } else {
                    // Extrae el cuerpo del error y lo muestra en el mensaje
                    val errorBody: String = response.bodyAsText()
                    _message.value = "Error al registrar: ${response.status.value} - $errorBody"
                    _isRegistering.value = false  // Vuelve a habilitar el botón en caso de error
                }
            } catch (e: Exception) {
                // Captura y muestra la excepción para identificar el problema
                _message.value = "Excepción al registrar: ${e.localizedMessage}"
                _isRegistering.value = false  // Vuelve a habilitar el botón en caso de error
            }
        }
    }
    fun isCorreoRegistrado(correo: String): Boolean {
        var isRegistered = false
        viewModelScope.launch {
            try {
                // Realiza la consulta al backend para verificar si el correo ya está registrado
                val response: HttpResponse = client.get("$supabaseUrl/cliente?correo=eq.$correo") {
                    header("apikey", supabaseApiKey)
                    header("Authorization", "Bearer $supabaseApiKey")
                }

                if (response.status == HttpStatusCode.OK) {
                    val body = response.bodyAsText()
                    if (body.isNotEmpty()) {
                        // Si la respuesta no está vacía, significa que ya existe un registro con ese correo
                        isRegistered = true
                    }
                }
            } catch (e: Exception) {
                _message.value = "Error al verificar el correo: ${e.localizedMessage}"
            }
        }
        return isRegistered
    }

}
