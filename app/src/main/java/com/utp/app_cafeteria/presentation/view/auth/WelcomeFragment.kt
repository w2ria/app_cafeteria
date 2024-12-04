package com.utp.app_cafeteria.presentation.view.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.presentation.viewmodel.auth.WelcomeViewModel

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private lateinit var welcomeViewModel: WelcomeViewModel
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnRegistrarse: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView: View? = activity?.findViewById(R.id.nav_view)
        navView?.visibility = View.GONE

        // Inicializar el ViewModel
        welcomeViewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)

        // Asignar los botones
        btnIniciarSesion = view.findViewById(R.id.btnIniciarSesion)
        btnRegistrarse = view.findViewById(R.id.btnRegistrarse)

        // Observar el estado de si el usuario está logueado
        welcomeViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer { isLoggedIn ->
            if (isLoggedIn) {
                // Si el usuario está logueado, navegar al fragmento principal o actividad principal
                navigateToMain()
            }
        })

        // Configuración de los botones
        btnIniciarSesion.setOnClickListener {
            // Navegar al fragmento de login
            navigateToLogin()
        }

        btnRegistrarse.setOnClickListener {
            // Navegar al fragmento de registro
            navigateToRegister()
        }
    }

    // Método para navegar al fragmento de Login
    private fun navigateToLogin() {
        findNavController().navigate(R.id.login)
    }

    // Método para navegar al fragmento de Registro
    private fun navigateToRegister() {
        findNavController().navigate(R.id.register)
    }

    // Método para navegar al fragmento principal o la actividad principal
    private fun navigateToMain() {
        // Reemplaza esta acción con la acción que corresponda en tu `nav_graph.xml`
        findNavController().navigate(R.id.navigation_inicio) // o a tu fragmento principal
    }
}
