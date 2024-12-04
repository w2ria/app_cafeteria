package com.utp.app_cafeteria.data.entities

data class ProductoItem(
    val id_producto: Long,
    val nombre: String,
    val descripcion: String?,
    val id_categoria: Long,
    val imagen: String?,
    val precio: Double,
    val producto_carta: Boolean
)
