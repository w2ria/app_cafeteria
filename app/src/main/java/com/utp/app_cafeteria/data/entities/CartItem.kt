package com.utp.app_cafeteria.data.entities

data class CartItem(
    val productName: String,
    val productPrice: Double,
    val productQuantity: Int,
    val productCategory: String,
    val productImg: Int
)