package com.jesus.techzone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class Laptops : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_laptops, container, false)

        val boton = view.findViewById<Button>(R.id.btnOferta)

        boton.setOnClickListener {

            val miPaquete = Bundle()
            miPaquete.putString("1", "Oferta Especial PpCc")

            findNavController().navigate(R.id.gaming, miPaquete)

        }



        return view
    }


}