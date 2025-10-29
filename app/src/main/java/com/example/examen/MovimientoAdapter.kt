package com.example.examen

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovimientoAdapter(private val lista: List<Movimiento>) :
    RecyclerView.Adapter<MovimientoAdapter.ViewHolder>() {

    // ViewHolder con TextViews y ImageView
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvFecha: TextView = v.findViewById(R.id.tvFecha)
        val tvCantidad: TextView = v.findViewById(R.id.tvCantidad)
        val ivTipo: ImageView = v.findViewById(R.id.ivTipo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movimiento, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mov = lista[position]

        // Fecha y cantidad
        holder.tvFecha.text = mov.fecha
        holder.tvCantidad.text = "%.2f €".format(mov.cantidad)

        // Color e icono según tipo de movimiento
        if (mov.cantidad >= 0) {
            holder.tvCantidad.setTextColor(Color.parseColor("#2E7D32")) // verde
            holder.ivTipo.setImageResource(R.drawable.ic_ingreso)       // icono de ingreso
        } else {
            holder.tvCantidad.setTextColor(Color.parseColor("#C62828")) // rojo
            holder.ivTipo.setImageResource(R.drawable.ic_gasto)         // icono de gasto
        }
    }

    override fun getItemCount(): Int = lista.size
}
