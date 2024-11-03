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
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.databinding.FragmentInicioBinding
import com.utp.app_cafeteria.presentation.viewmodel.screens.home.InicioViewModel

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inicioViewModel =
            ViewModelProvider(this).get(InicioViewModel::class.java)

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        inicioViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

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