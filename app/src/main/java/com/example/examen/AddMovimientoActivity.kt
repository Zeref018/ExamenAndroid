package com.example.examen

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddMovimientoActivity : AppCompatActivity() {

    private lateinit var etCantidad: EditText
    private lateinit var rgTipo: RadioGroup
    private lateinit var rbIngreso: RadioButton
    private lateinit var rbGasto: RadioButton
    private lateinit var datePicker: DatePicker
    private lateinit var btnGuardar: Button
    private lateinit var dbHelper: DBHelper

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movimiento)

        // 1. Hacer que los iconos del sistema sean oscuros
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        // 2. Poner el fondo de la barra de estado blanco
        window.statusBarColor = Color.WHITE


        // Inicializar vistas
        etCantidad = findViewById(R.id.etCantidad)
        rgTipo = findViewById(R.id.rgTipo)
        rbIngreso = findViewById(R.id.rbIngreso)
        rbGasto = findViewById(R.id.rbGasto)
        datePicker = findViewById(R.id.datePicker)
        btnGuardar = findViewById(R.id.btnGuardar)
        dbHelper = DBHelper(this)

        btnGuardar.setOnClickListener {
            val cantidadInput = etCantidad.text.toString()
            if (cantidadInput.isEmpty()) {
                etCantidad.error = "Introduce cantidad"
                return@setOnClickListener
            }

            var cantidad = cantidadInput.toDouble()
            if (rbGasto.isChecked) {
                cantidad = -cantidad
            }

            // Obtener fecha del DatePicker
            val day = datePicker.dayOfMonth
            val month = datePicker.month + 1 // enero = 0
            val year = datePicker.year
            val fecha = String.format("%02d/%02d/%04d", day, month, year)

            // Insertar en DB
            dbHelper.insertarMovimiento(cantidad, fecha)

            Toast.makeText(this, "Movimiento guardado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
