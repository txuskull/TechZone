package com.jesus.techzone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class Moviles : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_moviles, container, false)


        val btnPixel = view.findViewById<Button>(R.id.btnPixel)
        val btnSamsung = view.findViewById<Button>(R.id.btnSamsung)

        btnPixel.setOnClickListener {
            CarritoDatos.productosSeleccionados.add(Producto("Google Pixel 8",
                700.0,
                "https://m.media-amazon.com/images/I/61bwiPRcv2L._AC_UF894,1000_QL80_.jpg"))
            Toast.makeText(requireContext(), "Añadido Pixel 📱", Toast.LENGTH_SHORT).show()
        }

        btnSamsung.setOnClickListener {
            CarritoDatos.productosSeleccionados.add(Producto("Samsung S24 Ultra",
                1200.0,
                "https://m.media-amazon.com/images/I/61bwiPRcv2L._AC_UF894,1000_QL80_.jpg"))
            Toast.makeText(requireContext(), "Añadido Samsung 🌌", Toast.LENGTH_SHORT).show()
        }

        return view
    }


}