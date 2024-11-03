package com.utp.app_cafeteria.presentation.viewmodel.screens.order


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrdenesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Este es el fragmento de Ordenes"
    }
    val text: LiveData<String> = _text
}