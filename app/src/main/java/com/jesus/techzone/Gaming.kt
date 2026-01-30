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

        val miTexto = view.findViewById<TextView>(R.id.textoRecibido)
        val btnComprar = view.findViewById<Button>(R.id.btnComprar)

        val dato = arguments?.getString("1")

        if (dato != null){
            miTexto.text = dato
        }

        btnComprar.setOnClickListener {

            Toast.makeText(requireContext(), "¡Añadido al carrito!", Toast.LENGTH_SHORT).show()

        }


        return view
    }



}