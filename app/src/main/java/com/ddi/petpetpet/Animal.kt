package com.ddi.petpetpet


class Animal {
    var codigo:Int=0
    var nombre:String=""
    var raza:String=""
    var fecnac:String=""
    var sexo:String=""
    var dni:String=""

    constructor(
        codigo: Int,
        nombre: String,
        raza: String,
        fecnac: String,
        sexo: String,
        dni: String
    ) {
        this.codigo = codigo
        this.nombre = nombre
        this.raza = raza
        this.fecnac = fecnac
        this.sexo = sexo
        this.dni = dni
    }
}
/*class Animal(var codigo: Int, var nombre: String, var raza: String, var fecnac: String, var sexo: String) {
    override fun toString(): String {
        return "Código: $codigo, Nombre: $nombre, Raza: $raza, Fecha de nacimiento: $fecnac, Sexo: $sexo"
    }
}
*/