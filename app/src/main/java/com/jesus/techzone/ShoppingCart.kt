package com.jesus.techzone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ShoppingCart : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shopping_cart, container, false)

        // 1. Buscamos el TextView donde vamos a escribir
        val textoProducto = view.findViewById<TextView>(R.id.tvProductoRecibido)

        if (CarritoDatos.productosSeleccionados.isEmpty()){
            textoProducto.text = "El carrito esta vacio"
        }else{

            val lista = StringBuilder()

            for (i in CarritoDatos.productosSeleccionados){
                lista.append("$i agregado.\n\n")
            }

            // Esto hace el StringBuilder y el bucle por ti "por debajo"
            // separator = "\n" -> Pone un salto de línea entre cada producto
            //textoProducto.text = CarritoDatos.productosSeleccionados.joinToString(separator = "\n"){ "$it agregado."}

            textoProducto.text = lista.toString()

        }

        return view
    }


}