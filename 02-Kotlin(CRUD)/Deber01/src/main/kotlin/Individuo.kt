import java.io.Serializable

// Clase Individuo escrita en Kotlin
data class Individuo(
    // Atributos o propiedades de la clase
    var nombre: String, // Nombre o raza del individuo
    var edadP: Int, // Edad promedio del individuo
    var genero: Char, // Genero del individuo
    var pesoP: Double, // Peso promedio del individuo
    var alturaP: Double // Altura promedio del individuo
) : Serializable {
    // Métodos
    // Sobrescribir el método toString para proporcionar una representación de cadena personalizada
    override fun toString(): String {
        var output: String = "\n"
        output += "\tNombre: $nombre\n"
        output += "\tEdad: $edadP\n"
        output += "\tGenero: $genero\n"
        output += "\tPeso: $pesoP\n"
        output += "\tAltura: $alturaP\n"
        return output
    }

}