package com.utp.app_cafeteria.presentation.viewmodel.auth

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WelcomeViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData para gestionar el estado de si el usuario está logueado
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    init {
        checkIfUserIsLoggedIn()
    }

    // Método para verificar si el usuario está logueado
    private fun checkIfUserIsLoggedIn() {
        val sharedPrefs = getApplication<Application>().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        _isLoggedIn.value = sharedPrefs.getBoolean("is_logged_in", false)
    }

    // Método para actualizar el estado del login y guardarlo en SharedPreferences
    fun setUserLoggedIn(loggedIn: Boolean) {
        _isLoggedIn.value = loggedIn
        val sharedPrefs = getApplication<Application>().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean("is_logged_in", loggedIn).apply()
    }
}
