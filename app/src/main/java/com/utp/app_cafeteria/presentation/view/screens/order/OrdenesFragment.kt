package com.utp.app_cafeteria.presentation.view.screens.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.utp.app_cafeteria.databinding.FragmentOrdenesBinding
import com.utp.app_cafeteria.presentation.adapter.screens.ordenes.OrdenesAdapter
import com.utp.app_cafeteria.presentation.viewmodel.screens.order.OrdenesViewModel
import com.utp.app_cafeteria.R

class OrdenesFragment : Fragment() {

    private var _binding: FragmentOrdenesBinding? = null
    private val binding get() = _binding!!

    private lateinit var ordenesViewModel: OrdenesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ordenesViewModel = ViewModelProvider(this)[OrdenesViewModel::class.java]

        _binding = FragmentOrdenesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.ordenesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = false

        val adapter = OrdenesAdapter(emptyList())
        recyclerView.adapter = adapter

        // Observar la lista de órdenes
        ordenesViewModel.ordenesList.observe(viewLifecycleOwner) { ordenes ->
            println("Órdenes recibidas: ${ordenes.size}")
            adapter.updateData(ordenes)
        }

        // Observar mensajes de error o información
        /*ordenesViewModel.message.observe(viewLifecycleOwner) { mensaje ->
            binding.tvMensaje.text = mensaje
            binding.tvMensaje.visibility = if (mensaje.isEmpty()) View.GONE else View.VISIBLE
        }
*/
        // Llamar al ViewModel para obtener las órdenes
        ordenesViewModel.fetchOrdenes()

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