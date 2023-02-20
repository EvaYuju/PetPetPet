package com.ddi.petpetpet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ddi.petpetpet.databinding.ActivityRegistroAnimalBinding

class RegistroAnimalesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroAnimalBinding
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Vincula a la vista secundaria este código
        binding = ActivityRegistroAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Crear la base de datos
        dbHelper = DatabaseHelper(this, null)

        // Recupera el nombre de usuario del Intent que inició esta actividad
        val usuario = intent.getStringExtra("Usuario")
        // Actualiza el TextView con el nombre de usuario
        binding.usuarioLogeado.text = "Usuario: $usuario"

        // Asignar escuchadores de clic a los botones
        // Botón insertar
        binding.btnAlta.setOnClickListener {
            // Código para guardar el registro
            val codigo = binding.ptCodigo.text.toString()
            val nombre = binding.ptNombre.text.toString()
            val raza = binding.ptRaza.text.toString()
            val sexo = binding.ptSexo.text.toString()
            val fecnac = binding.ptFecNac.text.toString()
            val dniPropietario = binding.ptDNI.text.toString()

            // Verificar si todos los campos están llenos
            if (codigo.isNotEmpty() && nombre.isNotEmpty() && raza.isNotEmpty() && sexo.isNotEmpty() && fecnac.isNotEmpty() && dniPropietario.isNotEmpty()) {
                // Verificar si el código ya existe en la base de datos
                val dbHandler = DatabaseHelper(this, null)
                val animal = dbHandler.getAnimal(codigo)
                if (animal == null) {
                    // Insertar el nuevo animal en la base de datos
                    dbHandler.insertAnimal(codigo, nombre, raza, sexo, fecnac, dniPropietario)
                    // Limpiar los campos después de insertar
                    binding.ptCodigo.setText("")
                    binding.ptNombre.setText("")
                    binding.ptRaza.setText("")
                    binding.ptSexo.setText("")
                    binding.ptFecNac.setText("")
                    binding.ptDNI.setText("")
                    Toast.makeText(this, "Datos insertados", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "El código ya existe en la base de datos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón actualizar
        // Botón actualizar
        binding.btnModif.setOnClickListener {
            // Código para modificar el registro
            // Obtenemos los nuevos valores de los campos
            val codigo = binding.ptCodigo.text.toString()
            val nombre = binding.ptNombre.text.toString()
            val raza = binding.ptRaza.text.toString()
            val sexo = binding.ptSexo.text.toString()
            val fecnac = binding.ptFecNac.text.toString()
            val dniPropietario = binding.ptDNI.text.toString()

            val dbHandler = DatabaseHelper(this, null)
            val animal = dbHandler.getAnimal(codigo)

            if (animal != null) {
                // Actualizamos los datos del animal con los nuevos valores
                dbHandler.updateAnimal(codigo, nombre, raza, sexo, fecnac, dniPropietario)

                // Limpiamos los campos de entrada de texto
                binding.ptCodigo.text.clear()
                binding.ptNombre.text.clear()
                binding.ptRaza.text.clear()
                binding.ptSexo.text.clear()
                binding.ptFecNac.text.clear()
                binding.ptDNI.text.clear()

                // Mostramos un mensaje al usuario
                Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show()
            } else {
                // Si no se encuentra el animal en la base de datos, mostramos un mensaje de error
                Toast.makeText(this, "No se ha encontrado el animal con código $codigo", Toast.LENGTH_SHORT).show()
            }
        }
        // Botón borrar
        binding.btnBorrar.setOnClickListener {
            val codigo = binding.ptCodigo.text.toString().trim()

            val dbHandler = DatabaseHelper(this, null)
            val result = dbHandler.deleteAnimal(codigo)

            if (result != null) {
                Toast.makeText(this, "Registro borrado", Toast.LENGTH_SHORT).show()
                binding.ptCodigo.setText("")
                binding.ptNombre.setText("")
                binding.ptRaza.setText("")
                binding.ptSexo.setText("")
                binding.ptFecNac.setText("")
                binding.ptDNI.setText("")
            } else {
                Toast.makeText(this, "No se encontró el registro", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón leer
        // Botón consulta
        binding.btnConsul.setOnClickListener {
            // Código para consultar el registro
            val codigo = binding.ptCodigo.text.toString().trim()
            val dbHandler = DatabaseHelper(this, null)
            val cursor = dbHandler.getName()

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val dbCodigo = cursor.getString(cursor.getColumnIndexOrThrow("codigo"))
                    if (dbCodigo == codigo) {
                        val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                        val raza = cursor.getString(cursor.getColumnIndexOrThrow("raza"))
                        val sexo = cursor.getString(cursor.getColumnIndexOrThrow("sexo"))
                        val fecnac = cursor.getString(cursor.getColumnIndexOrThrow("fecnac"))
                        val dni = cursor.getString(cursor.getColumnIndexOrThrow("dni"))
                        binding.ptNombre.setText(nombre)
                        binding.ptRaza.setText(raza)
                        binding.ptSexo.setText(sexo)
                        binding.ptFecNac.setText(fecnac)
                        binding.ptDNI.setText(dni)
                        Toast.makeText(this, "Datos cargados", Toast.LENGTH_SHORT).show()
                        break
                    }
                } while (cursor.moveToNext())

                cursor.close()
                dbHandler.close()
            } else {

            Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show()}
        }

    }


}