package com.jesus.techzone

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    //private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //1. buscar y mostrar toolvar(PASO 1 FUNCIONA MENU TOOLBAR IZQDA)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // PASO 2 activar menu_main faltan navHostFragment y navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as androidx.navigation.fragment.NavHostFragment
        val navController = navHostFragment.navController
        bottomNav.setupWithNavController(navController)

        // PASO 2 Solucion Clase
        findViewById<View>(android.R.id.content).post {//falta esto
            val navController = findNavController(R.id.nav_host_fragment)
            val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav.setupWithNavController(navController)
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
            val builder = AppBarConfiguration.Builder(navController.graph)
            builder.setOpenableLayout(drawerLayout)
            val appBarConfiguration = builder.build()
            toolbar.setupWithNavController(navController, appBarConfiguration)// y esto
        }

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setupWithNavController(navController)

        // LISTENER: EL "ESPÍA" DE NAVEGACIÓN
        // Esto se ejecuta cada vez que cambias de pantalla automáticamente
        navController.addOnDestinationChangedListener { _, destination, _ ->

            // Comprobamos si el destino actual es el Carrito
            // (Asegúrate de que R.id.shoppingCart es el ID que le pusiste en tu nav_graph.xml)
            if (destination.id == R.id.shoppingCart) {

                // OCULTAR el menú de abajo (Lo más común en apps pro)
                bottomNav.visibility = View.GONE
            } else {
                // Si NO estamos en el carrito, asegurarnos de que el menú se vea
                bottomNav.visibility = View.VISIBLE
            }
        }

    }
    //1. ver menus(PASO 1 FUNCIONA MENU TOOLBAR IZQDA)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //1. conectar con navhost para moverse con los menus por los fragmentos(PASO 1 FUNCIONA MENU TOOLBAR IZQDA)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item)
    }



}