package com.jesus.techzone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView // <--- Importante

class ShoppingCart : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shopping_cart, container, false)

        // 1. Buscamos el hueco en la pantalla (El RecyclerView) en .xml
        val recycler = view.findViewById<RecyclerView>(R.id.rvCarrito)

        // 2. CONFIGURACIÓN OBLIGATORIA (LayoutManager)
        // Le decimos: "Quiero que los elementos salgan en una lista vertical normal"
        recycler.layoutManager = LinearLayoutManager(requireContext())

        // 3. CONECTAMOS LOS CABLES (Aquí ocurre la magia)
        // Creamos el adaptador y LE PASAMOS la lista de nuestro almacén (CarritoDatos)
        val adaptador = CarritoAdapter(CarritoDatos.productosSeleccionados)

        // 4. Enchufamos el adaptador al RecyclerView
        recycler.adapter = adaptador

        return view
    }
}