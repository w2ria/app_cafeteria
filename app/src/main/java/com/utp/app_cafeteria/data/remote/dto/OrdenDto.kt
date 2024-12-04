package com.utp.app_cafeteria.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrdenDto(
    @SerialName("id_orden")
    val idOrden: Long,
    @SerialName("id_cliente")
    val idCliente: Long?,
    @SerialName("id_estado")
    val idEstado: Long?,
    @SerialName("fecha")
    val fecha: String?,
    @SerialName("hora_reserva")
    val horaReserva: String?,
    @SerialName("metodo_pago")
    val metodoPago: Int?,
    @SerialName("total")
    val total: Double?
)