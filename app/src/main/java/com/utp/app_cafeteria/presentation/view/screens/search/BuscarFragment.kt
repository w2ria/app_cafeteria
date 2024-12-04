package com.utp.app_cafeteria.presentation.view.screens.search
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
import com.utp.app_cafeteria.databinding.FragmentBuscarBinding
import com.utp.app_cafeteria.presentation.adapter.screens.search.BuscarAdapter
import com.utp.app_cafeteria.presentation.viewmodel.screens.search.BuscarViewModel

class BuscarFragment : Fragment() {

    private var _binding: FragmentBuscarBinding? = null
    private val binding get() = _binding!!

    private lateinit var buscarViewModel: BuscarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        buscarViewModel = ViewModelProvider(this)[BuscarViewModel::class.java]

        _binding = FragmentBuscarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.buscarRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = false

        val adapter = BuscarAdapter(emptyList())
        recyclerView.adapter = adapter

        // Observar la lista de productos
        buscarViewModel.productosList.observe(viewLifecycleOwner) { productos ->
            println("Productos recibidos: ${productos.size}")
            adapter.updateData(productos)
        }

        // Llamar al ViewModel para obtener los productos
        buscarViewModel.fetchProductos()

       /* val fabCart: ImageView = binding.fabCart
        fabCart.setOnClickListener {
            findNavController().navigate(R.id.navigation_cart)
        }*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
