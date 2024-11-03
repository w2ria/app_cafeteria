package com.utp.app_cafeteria.presentation.viewmodel.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BuscarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Este es el fragmento de Buscar"
    }
    val text: LiveData<String> = _text
}