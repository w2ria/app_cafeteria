package com.utp.app_cafeteria.presentation.view.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.utp.app_cafeteria.R
import com.utp.app_cafeteria.presentation.viewmodel.auth.RegisterViewModel

class RegisterFragment : Fragment() {

    private val registerViewModel: RegisterViewModel by viewModels()

    private lateinit var nombreEditText: EditText
    private lateinit var correoEditText: EditText
    private lateinit var codigoEditText: EditText
    private lateinit var rolEditText: EditText
    private lateinit var contrasenaEditText: EditText
    private lateinit var confiContraEditText: EditText
    private lateinit var telefonoEditText: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var tvMensaje: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_register, container, false)

        // Inicializar vistas
        nombreEditText = binding.findViewById(R.id.inNombre)
        correoEditText = binding.findViewById(R.id.inCorreo)
        codigoEditText = binding.findViewById(R.id.inCodigo)
        rolEditText = binding.findViewById(R.id.inRol)
        contrasenaEditText = binding.findViewById(R.id.inContra)
        confiContraEditText = binding.findViewById(R.id.confiContra)
        telefonoEditText = binding.findViewById(R.id.inCell)
        btnRegistrar = binding.findViewById(R.id.btnRegistrar)
        tvMensaje = binding.findViewById(R.id.tvMensaje)

        // Deshabilitar los campos de código y rol
        codigoEditText.isEnabled = false
        rolEditText.isEnabled = false

        // Observadores de LiveData
        registerViewModel.message.observe(viewLifecycleOwner, Observer { message ->
            tvMensaje.text = message
            tvMensaje.visibility = View.VISIBLE
        })

        registerViewModel.isRegistering.observe(viewLifecycleOwner, Observer { isRegistering ->
            btnRegistrar.isEnabled = !isRegistering
        })

        // Acción de registro
        btnRegistrar.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val correo = correoEditText.text.toString()
            val codigo = codigoEditText.text.toString()
            val rol = rolEditText.text.toString()
            val contrasenia = contrasenaEditText.text.toString()
            val confiContra = confiContraEditText.text.toString()
            val telefono = telefonoEditText.text.toString()

            // Verificar si el correo ya está registrado
            if (registerViewModel.isCorreoRegistrado(correo)) {
                tvMensaje.text = "El correo ya está registrado."
                tvMensaje.visibility = View.VISIBLE
                // Deshabilitar el botón de registro
                btnRegistrar.isEnabled = false
            } else {
                // Deshabilitar el botón para evitar que lo presionen nuevamente
                btnRegistrar.isEnabled = false

                // Llamar al método de registro en el ViewModel si el correo no está registrado
                registerViewModel.registerCliente(nombre, correo, telefono, contrasenia, confiContra)
            }
        }


        // Listener para el correo
        correoEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val correo = s.toString()
                val (codigo, rol) = obtenerCodigoYrol(correo)

                if (codigo != null && rol != null) {
                    // Rellenar los campos automáticamente
                    codigoEditText.setText(codigo)
                    rolEditText.setText(rol)

                    // Deshabilitar los campos para evitar que sean modificados por el usuario
                    codigoEditText.isEnabled = false
                    rolEditText.isEnabled = false

                    telefonoEditText.isEnabled = true // Habilitar el campo de teléfono para ser editable
                } else {
                    // Si el correo no sigue el patrón esperado, deshabilitar los campos
                    codigoEditText.isEnabled = false
                    rolEditText.isEnabled = false
                    telefonoEditText.isEnabled = false // Deshabilitar el teléfono
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        return binding
    }

    // Función que obtiene el código y rol basado en el correo
    private fun obtenerCodigoYrol(correo: String): Pair<String?, String?> {
        val codigoPattern = Regex("^[uU](\\d{8})$|^[cC](\\d{5})$")
        val matchResult = codigoPattern.find(correo.substringBefore("@"))

        return if (matchResult != null) {
            // Extraer el código y el rol
            val codigo = matchResult.value
            val rol = if (codigo.startsWith("u", ignoreCase = true)) {
                "Estudiante"
            } else {
                "Profesor"
            }
            Pair(codigo, rol)
        } else {
            // Si no cumple con el patrón, devolver null
            Pair(null, null)
        }
    }
}
