package com.example.examen

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "MovimientosDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE Movimientos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "cantidad REAL, " +
                    "fecha TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Movimientos")
        onCreate(db)
    }

    fun insertarMovimiento(cantidad: Double, fecha: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("cantidad", cantidad)
        values.put("fecha", fecha)
        db.insert("Movimientos", null, values)
        db.close()
    }

    fun obtenerMovimientos(): List<Movimiento> {
        val lista = mutableListOf<Movimiento>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Movimientos ORDER BY id DESC", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val cantidad = cursor.getDouble(1)
                val fecha = cursor.getString(2)
                lista.add(Movimiento(id, cantidad, fecha))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lista
    }
}
