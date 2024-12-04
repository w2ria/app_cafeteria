package com.utp.app_cafeteria.presentation.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    // LiveData para errores en el correo
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError

    // LiveData para el estado del login
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    // Validar el correo (ahora privado)
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

    // Realizar el login
    fun login(email: String, password: String) {
        val isEmailValid = validateEmail(email)
        _loginSuccess.value = isEmailValid && password.isNotEmpty()
    }
}
