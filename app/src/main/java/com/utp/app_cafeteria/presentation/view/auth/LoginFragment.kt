package com.utp.app_cafeteria.presentation.view.auth

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.presentation.viewmodel.auth.LoginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Vincular elementos de la UI
        emailField = view.findViewById(R.id.inCorreo)
        passwordField = view.findViewById(R.id.inContrasena)
        val loginButton = view.findViewById<View>(R.id.btnIniciarSesion)
        val backButton = view.findViewById<View>(R.id.btn_atras)
        val pgRegistro = view.findViewById<TextView>(R.id.pg_registro)


        // Observa errores en el correo
        loginViewModel.emailError.observe(viewLifecycleOwner) { error ->
            emailField.error = error
        }

        // Observa el estado del login
        loginViewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(requireContext(), "Login exitoso", Toast.LENGTH_SHORT).show()
                // Navegar al fragmento de inicio
                findNavController().navigate(R.id.navigation_inicio)
                val navView: View? = activity?.findViewById(R.id.nav_view)
                navView?.visibility = View.VISIBLE
            } else {
                Toast.makeText(requireContext(), "Login fallido. Verifica tus datos.", Toast.LENGTH_SHORT).show()
            }
        }


        // Configura la acción del botón de inicio de sesión
        loginButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            loginViewModel.login(email, password)
        }

        backButton.setOnClickListener {
            findNavController().popBackStack() // Regresar al fragmento anterior
        }

        pgRegistro.setOnClickListener {
            // Navegar al fragmento de Registro
            findNavController().navigate(R.id.register)
        }





    }
}
