package com.jesus.techzone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView // <--- Importante

class ShoppingCart : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shopping_cart, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.rvCarrito)
        val tvTotal = view.findViewById<TextView>(R.id.tvTotalPagar)

        recycler.layoutManager = LinearLayoutManager(requireContext())

        // ---------------------------------------------------------------
        // 1. CREAMOS LA LÓGICA DE CALCULAR (La función del aviso)
        // ---------------------------------------------------------------
        val actualizarPrecio = {
            // Calculamos la suma
            val suma = CarritoDatos.productosSeleccionados.sumOf { it.precio }
            // Actualizamos el texto
            tvTotal.text = "Total: $suma€"
        }

        // ---------------------------------------------------------------
        // 2. SE LA PASAMOS AL ADAPTADOR
        // ---------------------------------------------------------------
        // Fíjate que ahora pasamos dos cosas: la lista Y la función
        val adaptador = CarritoAdapter(CarritoDatos.productosSeleccionados, actualizarPrecio)

        recycler.adapter = adaptador

        // 3. LLAMAMOS A LA FUNCIÓN UNA VEZ AL PRINCIPIO
        // Para que calcule el total nada más entrar
        actualizarPrecio()





        val btnPagar = view.findViewById<Button>(R.id.btnFinalizarCompra)
        btnPagar.setOnClickListener {

            Toast.makeText(requireContext(), "🪙 Sablazo for you 🪙", Toast.LENGTH_LONG).show()
        }
        return view
    }
}