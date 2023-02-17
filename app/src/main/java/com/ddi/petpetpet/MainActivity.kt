package com.ddi.petpetpet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddi.petpetpet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Vincula la vista principal a este código
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Ocultar teclado
        binding.usuario.showSoftInputOnFocus = false
        binding.contrasena.showSoftInputOnFocus = false

        // comportamiento del botón "Login"

    }
}