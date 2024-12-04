package com.utp.app_cafeteria.domain.model

data class Orden(
    val idOrden: Long,
    val idCliente: Long?,
    val idEstado: Long?,
    val fecha: String?,
    val horaReserva: String?,
    val metodoPago: Int?,
    val total: Double?
)