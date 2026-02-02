package com.jesus.techzone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/*Si en el examen te piden un RecyclerView:
Creas la clase.
Escribes : class NombreClase : RecyclerView.Adapter<NombreClase.MiViewHolder>() {
Android Studio te pondrá una bombilla roja 💡.
Le das a "Implement Members" y él solo te escribe las
 3 funciones vacías. Tú solo tienes que rellenarlas.*/


// Esta clase es el CAMARERO. Recibe la lista de datos (nuestra lista de Strings)
// 1. LA CLASE PRINCIPAL (Siempre hereda de Adapter)
class CarritoAdapter(private val listaDatos: MutableList<Producto>,
                     private val onListaCambio: () -> Unit,
                     private val onProductoClick: (Producto) -> Unit) :
    RecyclerView.Adapter<CarritoAdapter.MiViewHolder>() {  // <--- ESTO ES FIJO

    // --- CLASE INTERNA (La Bandeja) ---
    // Esta clase sirve para "agarrar" los elementos del XML (el TextView, la Imagen...)
    // Se llama ViewHolder (Sujetador de Vistas)
    // 2. LA CLASE INTERNA (Siempre hereda de ViewHolder)
    class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+Aquí también buscaríamos la imagen o el botón de borrar si lo usáramos+
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        val titulo: TextView = view.findViewById(R.id.tvNombreItem)
        val borrar: LinearLayout = view.findViewById(R.id.btnBorrar)
        val imagen: ImageView = view.findViewById(R.id.imgIcono)

    }

    // --- MÉTODO  CREAR EL MOLDE (Inflar) ---
    // Este método se ejecuta solo unas pocas veces para crear los huecos visibles en pantalla
    // 3. ==============================FUNCIÓN CREAR (Copia y pega siempre igual)===================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        // "Inflamos" el XML de item_producto
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)// <--- Solo cambias el nombre del XML
        return MiViewHolder(vista)
    }

    // --- MÉTODO  RELLENAR DATOS (Bind) ---
    // Este método se ejecuta CADA VEZ que aparece una fila en pantalla.
    // Aquí es donde sustituimos el texto de ejemplo por el real.
    // 4. FUNCIÓN RELLENAR (Aquí está la lógica)
    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        // 1. Sacamos el dato de la lista según la posición (0, 1, 2...)
        val productoActual = listaDatos[position]
        // 2. Lo escribimos en el TextView del ViewHolder
        holder.titulo.text = "${productoActual.nombre} - ${productoActual.precio}"

        Glide.with(holder.itemView.context)
            .load(productoActual.imagenUrl)
            .into(holder.imagen)

        // --- LÓGICA DE BORRAR ---
        holder.borrar.setOnClickListener {

            // 1. Borramos el dato de la lista (del Singleton)
            listaDatos.removeAt(position)

            // 2. AVISAMOS AL CAMARERO (Muy Importante)
            // Si no haces esto, el dato se borra de memoria pero sigue pintado en pantalla
            // y la app crashea si tocas otro.
            notifyItemRemoved(position)

            // 3. Opcional: Avisar al adapter que los rangos han cambiado
            // (Para evitar bugs si borras el primero y luego quieres borrar el segundo)
            notifyItemRangeChanged(position, listaDatos.size)

            onListaCambio()
        }

        // --- LÓGICA DE DETALLE (CLIC EN LA TARJETA) --- <--- NUEVO
        // 'itemView' representa toda la fila. Si tocas en cualquier lado que no sea la papelera:
        holder.itemView.setOnClickListener {
            // Usamos el segundo teléfono y le pasamos el producto
            onProductoClick(productoActual)
        }
    }

    // --- MÉTODO  CONTAR ---
    // El RecyclerView nos pregunta: "¿Cuántas filas tengo que dibujar?"
    // 5. FUNCIÓN CONTAR (Casi siempre es .size)
    override fun getItemCount(): Int {
        return listaDatos.size
    }
}