package com.aquino.test

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aquino.test.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Retrieve user data
        val username = sharedPreferences.getString("username", "Usuario Desconocido")
        val email = sharedPreferences.getString("email", "Correo no disponible")

        // Display user data
        binding.tvUsername.text = "Nombre de Usuario: $username"
        binding.tvEmail.text = "Correo Electrónico: $email"

        // Logout Button
        binding.btnLogout.setOnClickListener {
            // Clear SharedPreferences (optional)
            sharedPreferences.edit().clear().apply()

            // Redirect to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}