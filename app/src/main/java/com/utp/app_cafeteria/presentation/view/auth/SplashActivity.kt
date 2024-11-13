package com.utp.app_cafeteria.presentation.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utp.app_cafeteria.MainActivity
import com.utp.app_cafeteria.R
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    // Define un Job para la coroutine
    private val splashJob = Job()
    private val splashScope = CoroutineScope(Dispatchers.Main + splashJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Cambia a tu XML splash

        // Ejecuta el retraso de 3 segundos
        splashScope.launch {
            delay(5000) // Espera 3 segundos
            // Después del retraso, inicia MainActivity
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            overridePendingTransition(R.anim.slide_fade_in, R.anim.fade_out)

            finish() // Finaliza el SplashActivity para que no regrese al presionar atrás
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        splashJob.cancel() // Cancela el job cuando la actividad se destruye
    }
}