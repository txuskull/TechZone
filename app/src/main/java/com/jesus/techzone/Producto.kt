package com.jesus.techzone

import java.io.Serializable

// Esto es una "Caja" que tiene dos compartimentos: nombre y precio.
data class Producto(

    val nombre: String,
    val precio: Double,
    val imagenUrl: String

): Serializable