package com.utp.app_cafeteria.presentation.view.screens.search

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.presentation.viewmodel.search.BuscarViewModel

class BuscarFragment : Fragment() {

    companion object {
        fun newInstance() = BuscarFragment()
    }

    private val viewModel: BuscarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_buscar, container, false)
    }
}