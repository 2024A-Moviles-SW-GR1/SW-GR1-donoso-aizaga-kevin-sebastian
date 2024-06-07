import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date

// Clase Especie escrita en Kotlin
data class Especie(
    // Atributos o propiedades de la clase
    var nombre: String, // Nombre de la especie
    var habitat: String, // Habitat de la especie
    var clasificacion: String, // Clasificacion de la especie
    var fechaDescubrimiento: Date, // Fecha de descubrimiento de la especie
    var enPeligroExtincion: Boolean, // Si la especie se encuentra peligro de extincion
    private var individuos: MutableList<Individuo> = mutableListOf() // Lista de individuos de la especie
) : Serializable {
    // Métodos
    // Método para añadir un individuo a la especie
    fun addIndividuo(individuo: Individuo) {
        individuos.add(individuo)
    }

    // Método para eliminar un individuo de la especie
    fun removeIndividuo(individuo: Individuo) {
        individuos.remove(individuo)
    }

    // Método para obtener la lista de individuos
    fun getIndividuos(): List<Individuo> {
        return individuos
    }

    // Sobrescribir el método toString para proporcionar una representación de cadena personalizada en formato de tabla
    override fun toString(): String {
        var output: String = "\n"
        output += "\tNombre: $nombre\n"
        output += "\tHábitat: $habitat\n"
        output += "\tClasificación: $clasificacion\n"
        output += "\tFecha de descubrimiento: ${SimpleDateFormat("dd/MM/yyyy").format(fechaDescubrimiento)}\n"
        output += "\t¿Está en peligro de extinción?: $enPeligroExtincion\n"
        return output
    }
}
