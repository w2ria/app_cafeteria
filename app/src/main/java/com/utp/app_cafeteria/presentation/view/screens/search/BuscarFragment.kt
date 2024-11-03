package com.utp.app_cafeteria.presentation.view.screens.search
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.utp.app_cafeteria.databinding.FragmentBuscarBinding
import com.utp.app_cafeteria.presentation.viewmodel.screens.search.BuscarViewModel

class BuscarFragment : Fragment() {

    private var _binding: FragmentBuscarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val buscarViewModel =
            ViewModelProvider(this).get(BuscarViewModel::class.java)

        _binding = FragmentBuscarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSearch
        buscarViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}