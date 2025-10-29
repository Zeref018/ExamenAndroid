package com.example.examen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: MovimientoAdapter
    private lateinit var rv: RecyclerView
    private lateinit var tvBalance: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Hacer que los iconos del sistema sean oscuros
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        // 2. Poner el fondo de la barra de estado blanco
        window.statusBarColor = Color.WHITE


        dbHelper = DBHelper(this)
        rv = findViewById(R.id.rvMovimientos)
        tvBalance = findViewById(R.id.tvBalance)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddMovimientoActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val movimientos = dbHelper.obtenerMovimientos()
        adapter = MovimientoAdapter(movimientos)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        val balance = movimientos.sumOf { it.cantidad }
        tvBalance.text = "Balance: %.2f €".format(balance)
    }
}
