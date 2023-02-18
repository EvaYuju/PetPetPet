package com.ddi.petpetpet

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "petpetpet.db"
        // Tabla usuario
        private const val TABLE_NAME_USU = "usuarios"
        private const val COLUMN_USUARIO = "usuario"
        private const val COLUMN_CONTRASENA = "contrasena"
        // usuario preregistrado
        private const val ADMIN_USERNAME = "admin"
        private const val ADMIN_PASSWORD = "admin"
        // Tabla animal
        private const val TABLE_NAME_ANI = "animales"
        private const val COLUMN_CODIGO = "codigo"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_RAZA = "raza"
        private const val COLUMN_FECNAC = "fecnac"
        private const val COLUMN_SEXO = "sexo"
        private const val COLUMN_DNI = "dni"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear tabla de usuarios
        val createTableU = ("CREATE TABLE $TABLE_NAME_USU (" +
                "$COLUMN_USUARIO TEXT," +
                "$COLUMN_CONTRASENA TEXT)")
        db?.execSQL(createTableU)
        // Insertar usuario administrador
        val values = ContentValues().apply {
            put(COLUMN_USUARIO, ADMIN_USERNAME)
            put(COLUMN_CONTRASENA, ADMIN_PASSWORD)
        }
        db?.insert(TABLE_NAME_USU, null, values)
        // Crear tabla de animales
        val createTableA = ("CREATE TABLE $TABLE_NAME_ANI (" +
                "$COLUMN_CODIGO INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NOMBRE TEXT," +
                "$COLUMN_RAZA TEXT," +
                "$COLUMN_FECNAC TEXT," +
                "$COLUMN_SEXO TEXT," +
                "$COLUMN_DNI TEXT)")
        db?.execSQL(createTableA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_USU")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_ANI")
        onCreate(db)
    }
    // Chequear el usuario
    fun checkUser(usuario: String, contrasena: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME_USU WHERE $COLUMN_USUARIO = ? AND $COLUMN_CONTRASENA = ?"
        val cursor = db.rawQuery(query, arrayOf(usuario, contrasena))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun insertUsuario(usuario: String, contrasena: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USUARIO, usuario)
        values.put(COLUMN_CONTRASENA, contrasena)
        val resultado = db.insert(TABLE_NAME_USU, null, values)
        db.close()
        return resultado
    }
    // Insertar un nuevo animal en la tabla "animales"
    fun insertAnimal(nombre: String, codigo: Int, raza: String, fecnac: String, sexo: String, dniPropietario: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_CODIGO, codigo)
        values.put(COLUMN_NOMBRE, nombre)
        values.put(COLUMN_RAZA, raza)
        values.put(COLUMN_FECNAC, fecnac)
        values.put(COLUMN_SEXO, sexo)
        values.put(COLUMN_DNI, dniPropietario)
        val resultado = db.insert(TABLE_NAME_ANI, null, values)
        db.close()
        return resultado
    }



}
