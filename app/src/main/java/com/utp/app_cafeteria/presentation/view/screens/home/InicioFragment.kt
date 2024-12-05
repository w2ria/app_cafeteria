package com.utp.app_cafeteria.presentation.view.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.databinding.FragmentInicioBinding
import com.utp.app_cafeteria.presentation.adapter.screens.inicio.CarouselAdapter
import com.utp.app_cafeteria.presentation.viewmodel.screens.home.InicioViewModel

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2
    private lateinit var carouselAdapter: CarouselAdapter

    private lateinit var inicioViewModel: InicioViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inicioViewModel = ViewModelProvider(this).get(InicioViewModel::class.java)

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configurar texto del ViewModel
        val textView: TextView = binding.textHome
        inicioViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Configurar funcionalidad del carrito
        val fabCart: ImageView = binding.fabCart
        fabCart.setOnClickListener {
            findNavController().navigate(R.id.navigation_cart)
        }

        // Configurar el carrusel (ViewPager2)
        viewPager = binding.viewPager
        inicioViewModel.carouselImages.observe(viewLifecycleOwner) { images ->
            carouselAdapter = CarouselAdapter(images)
            viewPager.adapter = carouselAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
