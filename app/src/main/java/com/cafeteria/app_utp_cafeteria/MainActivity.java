package com.cafeteria.app_utp_cafeteria;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculando el BottomNavigationView
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Configurar el NavHostFragment para manejar la navegación
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);
        NavController navController = navHostFragment.getNavController();
        // Vincular el BottomNavigationView con el NavController
        NavigationUI.setupWithNavController(navView, navController);
    }
}
