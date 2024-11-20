package com.utp.app_cafeteria.presentation.adapter.screens.ordenes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.data.entities.OrdenesITem

class OrdenesAdapter(private val ordenesList: List<OrdenesITem>) :
    RecyclerView.Adapter<OrdenesAdapter.OrdenesViewHolder>() {

    class OrdenesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val codigoOrdenTextView: TextView = itemView.findViewById(R.id.txtCodigoOrden)
        val estadoDetalleTextView: TextView = itemView.findViewById(R.id.txtEstadoDetalle)
        val fechaDetalleTextView: TextView = itemView.findViewById(R.id.txtFechaDetalle)
        val precioDetalleTextView: TextView = itemView.findViewById(R.id.txtPrecioDetalle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdenesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_order, parent, false)
        return OrdenesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrdenesViewHolder, position: Int) {
        val currentOrden = ordenesList[position]
        holder.codigoOrdenTextView.text = currentOrden.codigoOrden
        holder.estadoDetalleTextView.text = currentOrden.estado
        holder.fechaDetalleTextView.text = currentOrden.fecha
        holder.precioDetalleTextView.text = "S/ ${currentOrden.precio}"
    }

    override fun getItemCount(): Int {
        return ordenesList.size
    }
}
