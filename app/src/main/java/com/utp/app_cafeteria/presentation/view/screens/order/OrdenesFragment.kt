package com.utp.app_cafeteria.presentation.view.screens.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.data.entities.OrdenesITem
import com.utp.app_cafeteria.databinding.FragmentOrdenesBinding
import com.utp.app_cafeteria.presentation.adapter.screens.ordenes.OrdenesAdapter
import com.utp.app_cafeteria.presentation.view.screens.cart.CartFragment
import com.utp.app_cafeteria.presentation.viewmodel.screens.order.OrdenesViewModel

class OrdenesFragment : Fragment() {

    private var _binding: FragmentOrdenesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val OrdenesViewModel =
            ViewModelProvider(this).get(OrdenesViewModel::class.java)

        _binding = FragmentOrdenesBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textOrder
//        OrdenesViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        // Configurar RecyclerView
        val recyclerView = binding.ordenesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = false

        // Crear datos de ejemplo (reemplazar con datos reales si se conectan a ViewModel)
        val ordenesList = listOf(
            OrdenesITem("OR123456", "Pendiente", "15/11/2024", 150.50),
            OrdenesITem("OR654321", "Completado", "14/11/2024", 200.75),
            OrdenesITem("OR789012", "Cancelado", "13/11/2024", 99.99),
            OrdenesITem("OR123456", "Pendiente", "15/11/2024", 150.50),
            OrdenesITem("OR654321", "Completado", "14/11/2024", 200.75),
            OrdenesITem("OR789012", "Cancelado", "13/11/2024", 99.99),
            OrdenesITem("OR123456", "Pendiente", "15/11/2024", 150.50),
            OrdenesITem("OR654321", "Completado", "14/11/2024", 200.75),
            OrdenesITem("OR789012", "Cancelado", "13/11/2024", 99.99),
            OrdenesITem("OR123456", "Pendiente", "15/11/2024", 150.50),
            OrdenesITem("OR654321", "Completado", "14/11/2024", 200.75),
            OrdenesITem("OR789012", "Cancelado", "13/11/2024", 99.99),
            OrdenesITem("OR123456", "Pendiente", "15/11/2024", 150.50),
            OrdenesITem("OR654321", "Completado", "14/11/2024", 200.75),
            OrdenesITem("OR789012", "Cancelado", "13/11/2024", 99.99),
        )

        // Configurar adaptador
        val adapter = OrdenesAdapter(ordenesList)
        recyclerView.adapter = adapter


        val fabCart: ImageView = binding.fabCart
        fabCart.setOnClickListener {
            findNavController().navigate(R.id.navigation_cart)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}