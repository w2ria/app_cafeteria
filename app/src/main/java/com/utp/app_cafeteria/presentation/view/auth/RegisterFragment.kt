package com.utp.app_cafeteria.presentation.view.auth

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.presentation.viewmodel.auth.RegisterViewModel
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    //private val viewModel: RegisterViewModel by viewModels()
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        val etNombre = view.findViewById<EditText>(R.id.inNombre)
        val etCorreo = view.findViewById<EditText>(R.id.inCorreo)
        val etCodigo = view.findViewById<EditText>(R.id.inCodigo)
        val etRol = view.findViewById<EditText>(R.id.inRol)
        val etTelefono = view.findViewById<EditText>(R.id.inCell)
        val etContrasenia = view.findViewById<EditText>(R.id.confiContra)
        val btnRegistrar = view.findViewById<Button>(R.id.btnRegistrar)
        val tvMensaje = view.findViewById<TextView>(R.id.tvMensaje)

        // Deshabilitar el campo de rol para que no sea editable
        etRol.isEnabled = false

        // Observar el cambio en el rol y actualizar el campo de rol automáticamente
        viewModel.rol.observe(viewLifecycleOwner) { rol ->
            etRol.setText(rol)  // Actualiza el campo de rol con el valor asignado
        }

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val correo = etCorreo.text.toString()
            val codigo = etCodigo.text.toString()
            val telefono = etTelefono.text.toString()
            val contrasenia = etContrasenia.text.toString()

            viewModel.registerCliente(nombre, correo, codigo, telefono, contrasenia)
        }

        // Observar el mensaje de registro y mostrarlo
        viewModel.message.observe(viewLifecycleOwner) { mensaje ->
            tvMensaje.text = mensaje
            tvMensaje.visibility = View.VISIBLE
        }

    }
}
