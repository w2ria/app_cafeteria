package com.utp.app_cafeteria.presentation.adapter.screens.cart
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.data.entities.CartItem

class CartAdapter(private val cartItems: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // viewholder_cart.xml
        val productNameTextView: TextView = itemView.findViewById(R.id.txtNombreProducto)
        val productPriceTextView: TextView = itemView.findViewById(R.id.txtPrecio)
        val productQuantityTextView: TextView = itemView.findViewById(R.id.txtCantidad)
        val productCategoryTextView: TextView = itemView.findViewById(R.id.txtCategoriaProducto)
        val productImgTextView: ImageView = itemView.findViewById(R.id.productoImg)

        // ... agregar más elementos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_cart, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.productNameTextView.text = currentItem.productName.uppercase()
        holder.productPriceTextView.text =  " S/ ${currentItem.productPrice} "
        holder.productQuantityTextView.text = "Cantidad: ${currentItem.productQuantity}"
        holder.productCategoryTextView.text =  currentItem.productCategory.uppercase()
        holder.productImgTextView.setImageResource(currentItem.productImg)
        // ... agregar más elementos
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}