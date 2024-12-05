package com.utp.app_cafeteria.presentation.viewmodel.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utp.app_cafeteria.R

class InicioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenido a la pantalla de inicio"
    }
    val text: LiveData<String> = _text

    // LiveData para las imágenes del carrusel
    private val _carouselImages = MutableLiveData<List<Int>>().apply {
        value = listOf(
            R.drawable.carrusel1,
            R.drawable.carrusel2,
            R.drawable.carrusel3,
            R.drawable.carrusel4,
            R.drawable.carrusel5,
            R.drawable.carrusel6
        )
    }
    val carouselImages: LiveData<List<Int>> = _carouselImages
}
