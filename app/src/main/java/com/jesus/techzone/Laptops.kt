package com.jesus.techzone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class Laptops : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_laptops, container, false)

        // 1. Buscamos los botones
        val btnMac = view.findViewById<Button>(R.id.btnMacbook)
        val btnLenovo = view.findViewById<Button>(R.id.btnLenovo)

        // 2. Lógica Botón 1
        btnMac.setOnClickListener {
            CarritoDatos.productosSeleccionados.add("MacBook Pro M2 - 1500€")
            Toast.makeText(requireContext(), "Añadido Mac 🍏", Toast.LENGTH_SHORT).show()
        }

        // 3. Lógica Botón 2
        btnLenovo.setOnClickListener {
            CarritoDatos.productosSeleccionados.add("Lenovo ThinkPad - 900€")
            Toast.makeText(requireContext(), "Añadido Lenovo 💻", Toast.LENGTH_SHORT).show()
        }



        return view
    }


}