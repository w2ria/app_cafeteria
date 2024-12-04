package com.utp.app_cafeteria.presentation.viewmodel.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.utp.app_cafeteria.core.utils.TimeAdapter
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.launch
import java.sql.Time

import com.utp.app_cafeteria.BuildConfig
import com.utp.app_cafeteria.data.entities.ProductoItem

val supabaseUrl = BuildConfig.SUPABASE_URL
val supabaseApiKey = BuildConfig.SUPABASE_KEY
class BuscarViewModel : ViewModel() {

    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd")
        .create()

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson {
                registerTypeAdapter(Time::class.java, TimeAdapter())
            }
        }
    }

    private val _productosList = MutableLiveData<List<ProductoItem>>()
    val productosList: LiveData<List<ProductoItem>> get() = _productosList

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    fun fetchProductos() {
        viewModelScope.launch {
            try {
                val response: HttpResponse = client.get("$supabaseUrl/rest/v1/producto") {
                    header("apikey", supabaseApiKey)
                    header("Authorization", "Bearer $supabaseApiKey")
                    contentType(ContentType.Application.Json)
                }

                if (response.status == HttpStatusCode.OK) {
                    val productos = response.body<List<ProductoItem>>()
                    if (productos.isEmpty()) {
                        _message.value = "No hay productos registrados"
                        _productosList.value = emptyList()
                    } else {
                        _productosList.value = productos
                    }
                } else {
                    val errorBody = response.bodyAsText()
                    println("Response Body: $errorBody")
                    _message.value = "Error al obtener productos: ${response.status.value} - $errorBody"
                    _productosList.value = emptyList()
                }
            } catch (e: Exception) {
                _message.value = "Excepción al obtener productos: ${e.localizedMessage}"
                println("Excepción: ${e.stackTraceToString()}")
                _productosList.value = emptyList()
            }
        }
    }
}
