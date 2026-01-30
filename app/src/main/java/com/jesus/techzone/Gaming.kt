package com.jesus.techzone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class Gaming : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gaming, container, false)

        val btnMsi = view.findViewById<Button>(R.id.btnMsi)
        val btnAlien = view.findViewById<Button>(R.id.btnAlienware)

        btnMsi.setOnClickListener {
            CarritoDatos.productosSeleccionados.add("MSI Raider GE76 - 2500€")
            Toast.makeText(requireContext(), "Añadido MSI 🐉", Toast.LENGTH_SHORT).show()
        }

        btnAlien.setOnClickListener {
            CarritoDatos.productosSeleccionados.add("Alienware Aurora - 3000€")
            Toast.makeText(requireContext(), "Añadido Alienware 👽", Toast.LENGTH_SHORT).show()
        }


        return view
    }



}