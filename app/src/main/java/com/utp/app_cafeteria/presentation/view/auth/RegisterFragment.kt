package com.utp.app_cafeteria.presentation.view.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.databinding.FragmentRegisterBinding
import com.utp.app_cafeteria.presentation.viewmodel.auth.RegisterViewModel

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val registerViewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        // Observa los cambios en los datos del ViewModel
        registerViewModel.emailError.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.inCorreo.error = it
            }
        }

        registerViewModel.passwordError.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.inContra.error = it
            }
        }

        registerViewModel.registerSuccess.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                // Navegar al siguiente fragmento o pantalla
            } else {
                Toast.makeText(context, "Error al registrar", Toast.LENGTH_SHORT).show()
            }
        }

        // Configura el botón de registro
        binding.btnIniciarSesion.setOnClickListener {
            val name = binding.inNombre.text.toString().trim()
            val email = binding.inCorreo.text.toString().trim()
            val password = binding.inContra.text.toString().trim()
            val phone = binding.inCell.text.toString().trim()

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(
                    phone
                )
            ) {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT)
                    .show()
            } else {
                registerViewModel.registerClient(name, email, password, phone)
            }
        }
        binding.btnAtras.setOnClickListener {
            findNavController().popBackStack() // Regresar al fragmento anterior
        }

        binding.pgIniciar.setOnClickListener {
            findNavController().navigate(R.id.login)
        }




        return binding.root
    }
}

