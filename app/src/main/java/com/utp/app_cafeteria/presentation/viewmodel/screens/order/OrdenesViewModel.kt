package com.utp.app_cafeteria.presentation.viewmodel.screens.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.utp.app_cafeteria.core.utils.TimeAdapter
import com.utp.app_cafeteria.data.entities.OrdenesITem
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


val supabaseUrl = BuildConfig.SUPABASE_URL
val supabaseApiKey = BuildConfig.SUPABASE_KEY


/*
private val supabaseUrl = "https://zlsoelzoxgfvzzthkkff.supabase.co/rest/v1"
private val supabaseApiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inpsc29lbHpveGdmdnp6dGhra2ZmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzI0OTA2OTIsImV4cCI6MjA0ODA2NjY5Mn0.pnfXzXxRFVyJJZpWyY2O8_XOTvWfIxJ9eNOLNN8ywdo"

*/

class OrdenesViewModel : ViewModel() {

    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd")
        .create()

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson{
                registerTypeAdapter(Time::class.java, TimeAdapter())
            }
        }
    }

    private val _ordenesList = MutableLiveData<List<OrdenesITem>>()
    val ordenesList: LiveData<List<OrdenesITem>> get() = _ordenesList

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    fun fetchOrdenes() {
        viewModelScope.launch {
            try {
                val response: HttpResponse = client.get("$supabaseUrl/rest/v1/orden") {
                    header("apikey", supabaseApiKey)
                    header("Authorization", "Bearer $supabaseApiKey")
                    contentType(ContentType.Application.Json)
                }

                if (response.status == HttpStatusCode.OK) {
                    val ordenes = response.body<List<OrdenesITem>>()
                    if (ordenes.isEmpty()) {
                        _message.value = "No hay órdenes registradas"
                        _ordenesList.value = emptyList()
                    } else {
                        _ordenesList.value = ordenes
                    }
                } else {
                    val errorBody = response.bodyAsText()
                    println("Response Body: $errorBody")
                    _message.value = "Error al obtener órdenes: ${response.status.value} - $errorBody"
                    _ordenesList.value = emptyList()
                }
            } catch (e: Exception) {
                _message.value = "Excepción al obtener órdenes: ${e.localizedMessage}"
                println("Excepción: ${e.stackTraceToString()}")
                _ordenesList.value = emptyList()
            }
        }
    }

    suspend fun obtenerOrdenesAll(): HttpResponse {
        return client.post("$supabaseUrl/rest/v1/obtener_ordenes_all") {
            headers {
                append("apikey", BuildConfig.SUPABASE_KEY)
                append("Authorization", "Bearer $supabaseApiKey")
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            }
        }
    }


    suspend fun obtenerOrdenConIdCliente(idCliente: Int): HttpResponse {
        return client.get("$supabaseUrl/rest/v1/obtener_ordenes_by_idcliente") {
            parameter("id_cliente", idCliente)
            headers {
                append("apikey", BuildConfig.SUPABASE_KEY)
                append("Authorization", "Bearer $supabaseApiKey")
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            }
        }
    }



}