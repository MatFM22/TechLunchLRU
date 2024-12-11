package com.aquino.test

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        // Obtener los datos del platillo
        val dishName = intent.getStringExtra("dish_name")
        val dishPrice = intent.getDoubleExtra("dish_price", 0.0)


        // Mostrar el nombre y precio del platillo (opcional)
        findViewById<TextView>(R.id.tvDishName).text = "Platillo: $dishName"
        findViewById<TextView>(R.id.tvDishPrice).text = "Precio: S/. $dishPrice"


        val nameEditText = findViewById<TextInputEditText>(R.id.etName)
        val surnameEditText = findViewById<TextInputEditText>(R.id.etSurname)
        val dateEditText = findViewById<TextInputEditText>(R.id.etDate)
        val timeEditText = findViewById<TextInputEditText>(R.id.etTime)
        val paymentEditText = findViewById<TextInputEditText>(R.id.etPayment)
        val phoneEditText = findViewById<TextInputEditText>(R.id.etPhone)

        // Configurar DatePickerDialog para la fecha
        dateEditText.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    // Formatear la fecha para mostrarla en el campo de texto
                    dateEditText.setText("$dayOfMonth/${month + 1}/$year")
                },
                2024, 1, 1 // Año, mes, día inicial
            )
            datePickerDialog.show()
        }

        // Configurar TimePickerDialog para la hora
        timeEditText.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    // Formatear la hora para mostrarla en el campo de texto
                    timeEditText.setText(String.format("%02d:%02d", hourOfDay, minute))
                },
                12, 0, true // Hora, minuto, AM/PM
            )
            timePickerDialog.show()
        }

        findViewById<MaterialButton>(R.id.btnReserve).setOnClickListener {
            val name = nameEditText.text.toString()
            val surname = surnameEditText.text.toString()
            val date = dateEditText.text.toString()
            val time = timeEditText.text.toString()
            val paymentMethod = paymentEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if (name.isNotEmpty() && surname.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty() && phone.isNotEmpty()) {
                val message = "Reserva Confirmada:\n\n" +
                        "Nombre: $name $surname\n" +
                        "Fecha: $date\n" +
                        "Hora: $time\n" +
                        "Método de pago: $paymentMethod\n\n" +
                        "Gracias por reservar con TecLunch."

                val url = "https://wa.me/$phone?text=${Uri.encode(message)}"

                // Abrir WhatsApp con el mensaje predefinido
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)

                // Procesar la reserva aquí (guardar, enviar a servidor, etc.)
                Toast.makeText(this, "Reserva realizada exitosamente", Toast.LENGTH_SHORT).show()
                finish()  // Finaliza la actividad y vuelve a la anterior
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}