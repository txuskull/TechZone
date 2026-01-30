package com.jesus.techzone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class SoporteTecnico : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_soporte_tecnico, container, false)

        // ===============================================================
        // LÓGICA DEL FORMULARIO
        // ===============================================================

        // 1. Buscamos los elementos
        val cajaTexto = view.findViewById<EditText>(R.id.inputProblema)
        val botonEnviar = view.findViewById<Button>(R.id.btnEnviarTicket)

        // 2. Al pulsar el botón...
        botonEnviar.setOnClickListener {

            // 3. LEEMOS LO QUE HA ESCRITO EL USUARIO
            val textoEscrito = cajaTexto.text.toString()

            // 4. Comprobamos si escribió algo (Validación básica)
            if (textoEscrito.isEmpty()) {
                // Si está vacío, le regañamos
                cajaTexto.error = "¡Escribe algo por favor!"
            } else {
                // Si escribió algo, simulamos el envío
                Toast.makeText(requireContext(), "Enviando ticket: $textoEscrito", Toast.LENGTH_LONG).show()

                // Opcional: Borramos el texto después de enviar
                cajaTexto.setText("")
            }
        }

        return view
    }


}