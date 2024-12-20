package com.utp.app_cafeteria.presentation.adapter.screens.ordenes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.data.entities.OrdenesITem

class OrdenesAdapter(private var ordenesList: List<OrdenesITem>) :
    RecyclerView.Adapter<OrdenesAdapter.OrdenesViewHolder>() {

    class OrdenesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val codigoOrdenTextView: TextView = itemView.findViewById(R.id.txtCodigoCategoriaa)
        val estadoDetalleTextView: TextView = itemView.findViewById(R.id.txtEstadoDetalle)
        val fechaDetalleTextView: TextView = itemView.findViewById(R.id.txtFechaDetalle)
        val precioDetalleTextView: TextView = itemView.findViewById(R.id.txtPrecioDetalle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdenesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_order, parent, false)
        return OrdenesViewHolder(itemView)
    }

    val estados = listOf(
        Pair(1, "pendiente"),
        Pair(2, "pendiente de pago"),
        Pair(3, "en proceso"),
        Pair(4, "pendiente de recojo"),
        Pair(5, "cancelado"),
        Pair(6, "completado")
    )

    val estadoMap = estados.toMap()


    override fun onBindViewHolder(holder: OrdenesViewHolder, position: Int) {
        val currentOrden = ordenesList[position]
        val estadoDescripcion = estadoMap[currentOrden.id_estado.toInt()] ?: "Estado desconocido"
        holder.codigoOrdenTextView.text = "OR000000${currentOrden.id_orden}"
        holder.estadoDetalleTextView.text = "${estadoDescripcion.uppercase()}"
        holder.fechaDetalleTextView.text = "${currentOrden.fecha}"
        holder.precioDetalleTextView.text = "S/ ${currentOrden.total}"
    }

    override fun getItemCount(): Int {
        return ordenesList.size
    }

    // Método para actualizar la lista de datos en el adaptador
    fun updateData(newOrdenesList: List<OrdenesITem>) {
        ordenesList = newOrdenesList
        notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
    }
}