package com.utp.app_cafeteria.data.entities

import java.sql.Date
import java.sql.Time

data class OrdenesITem (
    val id_orden: Int,
    val id_cliente: Int,
    val id_estado: Int,
    val descripcion_estado: String,
    val fecha: String,
    val hora_reserva: String,
    val metodo_pago: Int,
    val total: Double
)