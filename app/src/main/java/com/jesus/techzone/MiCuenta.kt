package com.jesus.techzone

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class MiCuenta : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflamos la vista
        val view = inflater.inflate(R.layout.fragment_mi_cuenta, container, false)

        // ===============================================================
        // AQUI EMPIEZA LO NUEVO
        // ===============================================================

        // 2. Buscamos el botón rojo
        val botonSalir = view.findViewById<Button>(R.id.btnCerrarSesion)

        botonSalir.setOnClickListener {

            // 3. CONSTRUIMOS LA VENTANA (Builder)
            // "requireContext()" es decirle a la ventana dónde tiene que aparecer
            val ventana = AlertDialog.Builder(requireContext())

            ventana.setTitle("Cerrar Sesión")
            ventana.setMessage("¿Estás seguro de que quieres salir?")

            // 4. BOTÓN "SÍ"
            ventana.setPositiveButton("Sí, salir") { _, _ ->
                // Esto se ejecuta si pulsa SÍ
                Toast.makeText(requireContext(), "Cerrando sesión...", Toast.LENGTH_SHORT).show()
            }

            // 5. BOTÓN "NO"
            ventana.setNegativeButton("Cancelar") { _, _ ->
                // Esto se ejecuta si pulsa NO (no hacemos nada, solo se cierra)
            }

            // 6. ¡MUY IMPORTANTE! MOSTRARLA
            ventana.create().show()
        }

        // ===============================================================

        return view
    }




}