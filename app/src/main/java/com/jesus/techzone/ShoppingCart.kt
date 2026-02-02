package com.jesus.techzone

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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
        val layoutVacio = view.findViewById<LinearLayout>(R.id.layoutVacio)

        recycler.layoutManager = LinearLayoutManager(requireContext())

        // 2. MODIFICAMOS LA LAMBDA (Ahora hace dos cosas)
        val actualizarTodo = {
            // A) CALCULAR PRECIO
            val suma = CarritoDatos.productosSeleccionados.sumOf { it.precio }
            tvTotal.text = "Total: $suma€"

            // B) CONTROLAR VISIBILIDAD (El Portero)
            if (CarritoDatos.productosSeleccionados.isEmpty()) {
                // Si está vacía: Oculta la lista, Muestra el aviso
                recycler.visibility = View.GONE
                layoutVacio.visibility = View.VISIBLE
                tvTotal.text = "Total: 0.0€" // Aseguramos que ponga 0
            } else {
                // Si hay cosas: Muestra la lista, Oculta el aviso
                recycler.visibility = View.VISIBLE
                layoutVacio.visibility = View.GONE
            }
        }

        // 1. Definimos la acción de ir al detalle
        val irAlDetalle = { producto: Producto ->

            // A) Preparamos la mochila (Bundle)
            val bundle = Bundle()
            bundle.putSerializable("producto_seleccionado", producto)

            // B) Navegamos llevando la mochila
            // Asegúrate de que R.id.detalleFragment es el ID correcto de tu nav_graph
            findNavController().navigate(R.id.fragment_detalle, bundle)
        }

        // 3. PASAMOS LA LAMBDA MEJORADA
        val adaptador = CarritoAdapter(CarritoDatos.productosSeleccionados,
            actualizarTodo, irAlDetalle)
        recycler.adapter = adaptador

        // 4. EJECUTAMOS AL INICIO
        actualizarTodo()


        val btnPagar = view.findViewById<Button>(R.id.btnFinalizarCompra)
        btnPagar.setOnClickListener {

            // 1. MEDIDA DE SEGURIDAD: ¿Hay algo que pagar?
            // Si el carrito está vacío, mostramos un aviso rápido (Toast) y no hacemos nada más.
            if (CarritoDatos.productosSeleccionados.isEmpty()) {
                Toast.makeText(requireContext(), "¡El carrito está vacío!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // "return" aquí significa "corta y sal de la función"
            }

            // 2. CREAMOS EL DIÁLOGO DE CONFIRMACIÓN
            // AlertDialog.Builder es la "fábrica" de ventanas
            val builder = AlertDialog.Builder(requireContext())

            builder.setTitle("Confirmar compra")
            builder.setMessage("¿Vas a pagar un total de ${tvTotal.text}?")

            // BOTÓN POSITIVO (SI)
            builder.setPositiveButton("Sí, pagar") { dialog, _ ->
                // A) Vaciamos el almacén de datos
                CarritoDatos.productosSeleccionados.clear()

                // B) Avisamos al adaptador de que la lista ha cambiado (para que se borre visualmente)
                // Ojo: adaptador.notifyDataSetChanged() sería lo normal, pero...
                // ...como tenemos la función 'actualizarTodo' que creamos ayer, ¡es mejor usarla!
                // Ella sola se encarga de poner el "Estado Vacío" y poner el precio a 0.
                actualizarTodo()

                // C) Feedback al usuario (Mensaje de éxito)
                Toast.makeText(requireContext(), "¡Compra realizada con éxito!", Toast.LENGTH_LONG).show()

                dialog.dismiss() // Cierra la ventana del diálogo
            }

            // BOTÓN NEGATIVO (NO)
            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss() // Simplemente cierra la ventana
            }

            // 3. ¡IMPORTANTE! MOSTRAR EL DIÁLOGO
            // Si se te olvida esta línea, nunca saldrá la ventana
            builder.show()
        }
        return view
    }
}