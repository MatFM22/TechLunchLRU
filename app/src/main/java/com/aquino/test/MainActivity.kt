package com.aquino.test

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Configurar el listener para los íconos de la barra de navegación
        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_platos -> {
                    // Si ya estás en la pantalla principal de los platos, no hacer nada
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_user_profile -> {
                    // Redirigir a la actividad del perfil de usuario
                    val intent = Intent(this, UserProfileActivity::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

        val dishes = listOf(
            Dish(name = "Lomo Saltado", price = 15.00, imageResId = R.drawable.techlunch),
            Dish(name = "Arroz Chaufa", price = 12.00, imageResId = R.drawable.techlunch),
            Dish(name = "Pescado Frito", price = 12.00, imageResId = R.drawable.techlunch),
            Dish(name = "Tallarines", price = 12.00, imageResId = R.drawable.techlunch),
            // Agrega más platos según sea necesario
        )
        /*
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DishAdapter(dishes) { dish ->
            // Acción de reservar cuando se hace clic en el botón
            Toast.makeText(this, "Reservaste: ${dish.name}", Toast.LENGTH_SHORT).show()

        }
        */

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = DishAdapter(dishes) { dish ->
            // Acción de reservar cuando se hace clic en el botón
            val intent = Intent(this, ReservationActivity::class.java)
            intent.putExtra("dish_name", dish.name)
            intent.putExtra("dish_price", dish.price)
            startActivity(intent)
        }
    }
}