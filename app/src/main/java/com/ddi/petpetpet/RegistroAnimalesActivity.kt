package com.ddi.petpetpet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddi.petpetpet.databinding.ActivityRegistroAnimalBinding

class RegistroAnimalesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroAnimalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Vincula a la vista secundaria este código
        binding = ActivityRegistroAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera el nombre de usuario del Intent que inició esta actividad
        val usuario = intent.getStringExtra("Usuario")

        // Actualiza el TextView con el nombre de usuario
        binding.usuarioLogeado.text = "Usuario: $usuario"
    }


}