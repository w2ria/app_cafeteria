package com.utp.app_cafeteria

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.utp.app_cafeteria.presentation.view.auth.SplashActivity
import com.utp.app_cafeteria.presentation.view.auth.RegisterFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vinculando el BottomNavigationView
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // Configurar el NavHostFragment para manejar la navegación
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        // Vincular el BottomNavigationView con el NavController
        NavigationUI.setupWithNavController(navView, navController)
    }
}
