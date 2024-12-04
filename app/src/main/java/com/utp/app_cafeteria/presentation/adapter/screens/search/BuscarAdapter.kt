package com.utp.app_cafeteria.presentation.adapter.screens.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.data.entities.ProductoItem


class BuscarAdapter(private var productosList: List<ProductoItem>) :
    RecyclerView.Adapter<BuscarAdapter.BuscarViewHolder>() {

    class BuscarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val codigoCategoriaTextView: TextView = itemView.findViewById(R.id.txtCodigoCategoriaa)
        val nombreProductoTextView: TextView = itemView.findViewById(R.id.txtNombreProducto)
        val tiempoProductoTextView: TextView = itemView.findViewById(R.id.txtTiempo)
        val precioProductoTextView: TextView = itemView.findViewById(R.id.txtPrecio)
        val productImgTextView: ImageView = itemView.findViewById(R.id.productoImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuscarViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_buscar, parent, false)
        return BuscarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BuscarViewHolder, position: Int) {
        val currentProducto = productosList[position]
        holder.codigoCategoriaTextView.text = "${currentProducto.id_categoria}"
        holder.nombreProductoTextView.text = currentProducto.nombre
        holder.tiempoProductoTextView.text = " 0 mins. "
        /*holder.tiempoProductoTextView.text = "${currentProducto.descripcion ?: 0} mins."*/
        holder.precioProductoTextView.text = " S/ ${currentProducto.precio} "
        /*holder.productImgTextView.setImageResource(currentProducto.imagen)*/

    }

    override fun getItemCount(): Int {
        return productosList.size
    }

    fun updateData(newProductosList: List<ProductoItem>) {
        productosList = newProductosList
        notifyDataSetChanged()
    }
}