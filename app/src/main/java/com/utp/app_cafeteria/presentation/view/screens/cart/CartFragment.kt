package com.utp.app_cafeteria.presentation.view.screens.cart

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.data.entities.CartItem
import com.utp.app_cafeteria.presentation.adapter.screens.cart.CartAdapter
import com.utp.app_cafeteria.presentation.viewmodel.screens.cart.CartViewModel

class CartFragment : Fragment(){
    companion object {
        fun newInstance() = CartFragment()
    }

    private val viewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        // regresar
        val btnRegresar = view.findViewById<ImageView>(R.id.txtBtnRegresar)
        btnRegresar.setOnClickListener {
            requireActivity().onBackPressed()
        }

        // mostrar data
        val recyclerView: RecyclerView = view.findViewById(R.id.cartRecyclerView)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(context)

        // crear data de ejemplo
        val cartItems = listOf(
            CartItem("PRODUCT 1", 10.0, 1, "Jugos",R.drawable.postres),
            CartItem("ARROZ A LA CUBANA 2", 20.0, 2, "Bebidas",R.drawable.postres),
            CartItem("POLLO A LA BRASA", 10.0, 1, "COMIDAS SALUDABLES",R.drawable.postres),
            CartItem("TRIPLE DE POLLO, QUESO Y JAMÓN", 20.0, 2, "Combos",R.drawable.postres),
            CartItem("TRIPLE DE POLLO, QUESO Y JAMÓN", 10.0, 1, "Sandwiches",R.drawable.postres),
            CartItem("ATRIPLE DE POLLO, QUESO Y JAMÓN", 20.0, 2, "Bebidas",R.drawable.postres),
            CartItem("TRIPLE DE POLLO, QUESO Y JAMÓN", 10.0, 1, "Bebidas",R.drawable.postres),
            CartItem("TRIPLE DE POLLO, QUESO Y JAMÓN", 20.0, 2, "Bebidas",R.drawable.postres),

        )

        val adapter = CartAdapter(cartItems)
        recyclerView.adapter = adapter

        return view
    }
}