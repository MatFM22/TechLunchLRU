package com.aquino.test

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<TextView>(R.id.tvGoToRegister).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            val email = findViewById<TextInputEditText>(R.id.etEmail).text.toString()
            val password = findViewById<TextInputEditText>(R.id.etPassword).text.toString()

            val sharedPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val savedEmail = sharedPrefs.getString("email", null)
            val savedPassword = sharedPrefs.getString("password", null)

            if (email == savedEmail && password == savedPassword) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, UserProfileActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }
    */

    val users = listOf(
        User(id = "123456", name = "Fernando Aquino", password = "password123"),
        User(id = "654321", name = "Juan Pérez", password = "123456"),
        // Agregar más usuarios según sea necesario
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<TextView>(R.id.tvGoToRegister).setOnClickListener {
            // Aquí ya no hace falta el registro
        }

        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            val userId = findViewById<TextInputEditText>(R.id.etEmail).text.toString()  // Usamos el ID en lugar del email
            val password = findViewById<TextInputEditText>(R.id.etPassword).text.toString()

            val user = users.find { it.id == userId && it.password == password }

            if (user != null) {
                // Si las credenciales son correctas, se muestra un mensaje y se redirige al perfil
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", user.name)  // Pasamos el nombre de usuario al perfil
                startActivity(intent)
                finish()

            } else {
                // Si las credenciales no coinciden
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}