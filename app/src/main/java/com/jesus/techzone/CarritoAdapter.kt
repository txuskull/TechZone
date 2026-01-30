package com.jesus.techzone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Esta clase es el CAMARERO. Recibe la lista de datos (nuestra lista de Strings)
class CarritoAdapter(private val listaDatos: MutableList<String>) :
    RecyclerView.Adapter<CarritoAdapter.MiViewHolder>() {

    // --- CLASE INTERNA (La Bandeja) ---
    // Esta clase sirve para "agarrar" los elementos del XML (el TextView, la Imagen...)
    // Se llama ViewHolder (Sujetador de Vistas)
    class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.tvNombreItem)
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+Aquí también buscaríamos la imagen o el botón de borrar si lo usáramos+
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }

    // --- MÉTODO 1: CREAR EL MOLDE (Inflar) ---
    // Este método se ejecuta solo unas pocas veces para crear los huecos visibles en pantalla
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        // "Inflamos" el XML de item_producto
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return MiViewHolder(vista)
    }

    // --- MÉTODO 2: RELLENAR DATOS (Bind) ---
    // Este método se ejecuta CADA VEZ que aparece una fila en pantalla.
    // Aquí es donde sustituimos el texto de ejemplo por el real.
    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        // 1. Sacamos el dato de la lista según la posición (0, 1, 2...)
        val productoActual = listaDatos[position]

        // 2. Lo escribimos en el TextView del ViewHolder
        holder.titulo.text = productoActual
    }

    // --- MÉTODO 3: CONTAR ---
    // El RecyclerView nos pregunta: "¿Cuántas filas tengo que dibujar?"
    override fun getItemCount(): Int {
        return listaDatos.size
    }
}