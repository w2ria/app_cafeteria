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

    val categorias = listOf(
        Pair(1, "Cafés"),
        Pair(2, "Jugos"),
        Pair(3, "Bebidas"),
        Pair(4, "Sandwiches"),
        Pair(5, "Postres"),
        Pair(6, "Desayunos"),
        Pair(7, "Combos"),
        Pair(8, "Snacks"),
        Pair(9, "Menú"),
        Pair(10, "Comidas Saludables"),
        Pair(11, "Especial del día"),
        Pair(12, "Pizza")
    )

    val categoriaMap = categorias.toMap()

    override fun onBindViewHolder(holder: BuscarViewHolder, position: Int) {
        val currentProducto = productosList[position]
        val nombreCategoria = categoriaMap[currentProducto.id_categoria.toInt()] ?: "Categoría desconocida"
        holder.codigoCategoriaTextView.text = "${nombreCategoria.uppercase()}"
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