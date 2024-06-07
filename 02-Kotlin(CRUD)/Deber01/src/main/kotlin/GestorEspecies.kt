import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class GestorEspecies(private val especiesFile: File, private val individuosFile: File) {

    // ATRIBUTOS DE LA CLASE GESTOR DE ESPECIES
    private var especies: MutableList<Especie> = mutableListOf()
    private var individuos: MutableList<Individuo> = mutableListOf()

    // INICIALIZADOR DE LA CLASE GESTOR DE ESPECIES
    init {
        especies = loadFromFile(especiesFile) ?: mutableListOf()
        individuos = loadFromFile(individuosFile) ?: mutableListOf()
    }

    // MÉTODOS CRUD PARA ESPECIES
    // Método para crear una especie
    fun createEspecie(especie: Especie) {
        especies.add(especie)
        saveToFile(especiesFile, especies)
    }

    // Método para buscar una especie
    fun readEspecie(nombre: String): Especie? {
        return especies.find { it.nombre == nombre }
    }

    // Método para actualizar una especie
    fun updateEspecie(nombre: String, updatedEspecie: Especie) {
        val index = especies.indexOfFirst { it.nombre == nombre }
        if (index != -1) {
            especies[index] = updatedEspecie
            saveToFile(especiesFile, especies)
        }
    }

    // Método para eliminar una especie
    fun deleteEspecie(nombre: String) {
        especies.removeAll { it.nombre == nombre }
        saveToFile(especiesFile, especies)
    }

    // MÉTODOS CRUD PARA INDIVIDUOS
    // Método para crear un individuo
    fun createIndividuo(especieNombre: String, individuo: Individuo) {
        val especie = readEspecie(especieNombre)
        especie?.let {
            it.addIndividuo(individuo)
            individuos.add(individuo)
            saveToFile(especiesFile, especies)
            saveToFile(individuosFile, individuos)
        }
    }

    // Método para leer un individuo
    fun readIndividuo(nombre: String): Individuo? {
        return individuos.find { it.nombre == nombre }
    }

    // Método para actualizar un individuo
    fun updateIndividuo(nombre: String, updatedIndividuo: Individuo) {
        val index = individuos.indexOfFirst { it.nombre == nombre }
        if (index != -1) {
            val oldIndividuo = individuos[index]
            individuos[index] = updatedIndividuo
            especies.find { it.getIndividuos().contains(oldIndividuo) }?.let { especie ->
                especie.removeIndividuo(oldIndividuo)
                especie.addIndividuo(updatedIndividuo)
                saveToFile(especiesFile, especies)
                saveToFile(individuosFile, individuos)
            }
        }
    }

    // Método para eliminar un individuo
    fun deleteIndividuo(nombre: String) {
        val individuo = readIndividuo(nombre)
        if (individuo != null) {
            individuos.remove(individuo)
            especies.forEach { especie ->
                especie.removeIndividuo(individuo)
            }
            saveToFile(especiesFile, especies)
            saveToFile(individuosFile, individuos)
        }
    }

    // MÉTODOS PARA LISTAR ESPECIES E INDIVIDUOS
    fun listEspecies(): List<String> {
        return especies.mapIndexed { index, especie -> "Especie: ${index + 1} ${especie.toString()}" }
    }

    fun listIndividuos(): List<String> {
        return individuos.mapIndexed { index, individuo -> "Individuo: ${index + 1} ${individuo.toString()}" }
    }

    // MÉTODOS PARA GUARDAR Y CARGAR DATOS DESDE ARCHIVOS
    private fun <T> saveToFile(file: File, data: T) {
        ObjectOutputStream(file.outputStream()).use { oos ->
            oos.writeObject(data)
        }
    }

    private fun <T> loadFromFile(file: File): T? {
        return if (file.exists()) {
            ObjectInputStream(file.inputStream()).use { ois ->
                @Suppress("UNCHECKED_CAST")
                ois.readObject() as? T
            }
        } else {
            null
        }
    }
}
