package com.jesus.techzone

// "object" significa que esto es ÚNICO en toda la app.
// Es como una caja fuerte accesible desde cualquier pantalla.
object CarritoDatos {
    // Aquí guardamos la lista de nombres de productos
    val productosSeleccionados = mutableListOf<Producto>()
}