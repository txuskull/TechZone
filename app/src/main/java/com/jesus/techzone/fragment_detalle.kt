package com.jesus.techzone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class fragment_detalle : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detalle, container, false)

        val tvNombre = view.findViewById<TextView>(R.id.tvDetalleNombre)
        val tvPrecio = view.findViewById<TextView>(R.id.tvDetallePrecio)

        // --- AQUÍ RECIBIMOS EL PAQUETE 📦 ---
        // "arguments" es la mochila que trae el fragmento.
        // Le preguntamos: "¿Traes algo llamado 'producto_seleccionado'?"
        val productoRecibido = arguments?.getSerializable("producto_seleccionado") as? Producto

        // Si realmente venía un producto, rellenamos los textos
        if (productoRecibido != null) {
            tvNombre.text = productoRecibido.nombre
            tvPrecio.text = "${productoRecibido.precio}€"
            // --- CARGAR FOTO GRANDE CON GLIDE ---
            // Buscamos la imagen del XML (asegúrate de que el ID coincide con tu XML)
            val imgGrande = view.findViewById<ImageView>(R.id.imgDetalle)

            Glide.with(this)
                .load(productoRecibido.imagenUrl)
                .into(imgGrande)
        }

        return view
    }


}