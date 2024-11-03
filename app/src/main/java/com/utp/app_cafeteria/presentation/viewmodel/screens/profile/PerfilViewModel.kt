package com.utp.app_cafeteria.presentation.viewmodel.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PerfilViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Este es el fragmento de Perfil"
    }
    val text: LiveData<String> = _text
}