import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    // Nombre de los archivos de datos de especies e individuos
    val nombreArchivoEspecie = "especies.csv"
    val nombreArchivoIndividuo = "individuos.csv"

    // Crear un objeto File para el archivo de especies
    val especiesFile = File("especies.csv")
    val individuosFile = File("individuos.csv")

    // Verificar si el archivo de especies existe y eliminarlo si es así
    if (especiesFile.exists()) {
        println("El archivo $nombreArchivoEspecie ya existe y será eliminado.")
        especiesFile.delete()
    } else {
        println("El archivo $nombreArchivoEspecie no existe.")
    }

    // Verificar si el archivo de individuos existe y eliminarlo si es así
    if (individuosFile.exists()) {
        println("El archivo $nombreArchivoIndividuo ya existe y será eliminado.")
        individuosFile.delete()
    } else {
        println("El archivo $nombreArchivoIndividuo no existe.")
    }

    // Crear un objeto File para el archivo de datos de especies
    val gestorEspecies = GestorEspecies(especiesFile, individuosFile)
    // Crear un objeto SimpleDateFormat para el formato de fecha
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    // Crear un objeto Scanner para leer la entrada del menu del usuario
    val scanner = Scanner(System.`in`)

    // Crear un objeto Scanner para leer la entrada tipo String del usuario
    val scannerString = Scanner(System.`in`)


    // INGRESO DE DATOS DE INDIVIDUOS PARA CADA ESPECIE
    // Individuos de la especie Canis lupus familiaris
    val individuo1 = Individuo("Pastor Alemán", 10, 'M', 30.0, 60.0)
    val individuo2 = Individuo("Pastor Alemán", 1, 'F', 20.0, 50.0)
    val individuo3 = Individuo("Pitbull Standford Shire Terrier", 5, 'M', 25.0, 55.0)
    val individuo4 = Individuo("Pitbull Standford Shire Terrier", 2, 'F', 15.0, 45.0)
    val individuo5 = Individuo("Rotweiler", 7, 'M', 35.0, 65.0)
    val individuo6 = Individuo("Rotweiler", 3, 'F', 25.0, 55.0)

    // Individuos de la especie Felis catus
    val individuo7 = Individuo("Siamés", 5, 'M', 5.0, 30.0)
    val individuo8 = Individuo("Siamés", 2, 'F', 4.0, 25.0)
    val individuo9 = Individuo("Persa", 3, 'M', 6.0, 35.0)
    val individuo10 = Individuo("Persa", 1, 'F', 5.0, 30.0)
    val individuo11 = Individuo("Bengala", 4, 'M', 7.0, 40.0)
    val individuo12 = Individuo("Bengala", 2, 'F', 6.0, 35.0)

    // INGRESO DE DATOS DE ESPECIES Y ASIGNACIÓN DE INDIVIDUOS
    // Especie Canis lupus familiaris con sus individuos
    val especie1 = Especie("Canis lupus familiaris", "Doméstico", "Mamífero", dateFormat.parse("01/01/1900"), false)
    gestorEspecies.createEspecie(especie1)
    gestorEspecies.createIndividuo(especie1.nombre, individuo1)
    gestorEspecies.createIndividuo(especie1.nombre, individuo2)
    gestorEspecies.createIndividuo(especie1.nombre, individuo3)
    gestorEspecies.createIndividuo(especie1.nombre, individuo4)
    gestorEspecies.createIndividuo(especie1.nombre, individuo5)
    gestorEspecies.createIndividuo(especie1.nombre, individuo6)

    // Especie Felis catus con sus individuos
    val especie2 = Especie("Felis catus", "Doméstico", "Mamífero", dateFormat.parse("01/01/1900"), false)
    gestorEspecies.createEspecie(especie2)
    gestorEspecies.createIndividuo(especie2.nombre, individuo7)
    gestorEspecies.createIndividuo(especie2.nombre, individuo8)
    gestorEspecies.createIndividuo(especie2.nombre, individuo9)
    gestorEspecies.createIndividuo(especie2.nombre, individuo10)
    gestorEspecies.createIndividuo(especie2.nombre, individuo11)
    gestorEspecies.createIndividuo(especie2.nombre, individuo12)

    // MENÚ PRINCIPAL DEL PROGRAMA
    while (true) {
        println("------ Menú Principal ------")
        println("1. Crear Especie")
        println("2. Buscar Especie")
        println("3. Actualizar Especie")
        println("4. Eliminar Especie")
        println("5. Crear Individuo")
        println("6. Buscar Individuo")
        println("7. Actualizar Individuo")
        println("8. Eliminar Individuo")
        println("9. Listar Especies")
        println("10. Listar Individuos")
        println("11. Salir")
        print("Seleccione una opción: ")

        val option = scanner.nextInt()

        when (option) {
            1 -> {
                println("Ingrese los datos de la especie:")
                print("Nombre: ")
                val nombre = scanner.next()
                print("Hábitat: ")
                val habitat = scanner.next()
                print("Clasificación: ")
                val clasificacion = scanner.next()
                print("Fecha de descubrimiento (dd/MM/yyyy): ")
                val fechaDescubrimiento = dateFormat.parse(scanner.next())
                print("¿Está en peligro de extinción? (true/false): ")
                val enPeligroExtincion = scanner.nextBoolean()
                val especie = Especie(nombre, habitat, clasificacion, fechaDescubrimiento, enPeligroExtincion)
                gestorEspecies.createEspecie(especie)
            }
            2 -> {
                print("Ingrese el nombre de la especie a leer: ")
                val nombre: String = scannerString.nextLine()
                val especie = gestorEspecies.readEspecie(nombre)
                println(especie ?: "Especie no encontrada.")
            }
            3 -> {
                print("Ingrese el nombre de la especie a actualizar: ")
                val nombre: String = scannerString.nextLine()
                val especie = gestorEspecies.readEspecie(nombre)
                if (especie != null) {
                    println("Ingrese los nuevos datos de la especie:")
                    print("Nombre: ")
                    especie.nombre = scanner.next()
                    print("Hábitat: ")
                    especie.habitat = scanner.next()
                    print("Clasificación: ")
                    especie.clasificacion = scanner.next()
                    print("Fecha de descubrimiento (dd/MM/yyyy): ")
                    especie.fechaDescubrimiento = dateFormat.parse(scanner.next())
                    print("¿Está en peligro de extinción? (true/false): ")
                    especie.enPeligroExtincion = scanner.nextBoolean()
                    gestorEspecies.updateEspecie(nombre, especie)
                } else {
                    println("Especie no encontrada.")
                }
            }
            4 -> {
                print("Ingrese el nombre de la especie a eliminar: ")
                val nombre: String = scannerString.nextLine()
                gestorEspecies.deleteEspecie(nombre)
            }
            5 -> {
                print("Ingrese el nombre de la especie a la que pertenece el individuo: ")
                val especieNombre = scanner.next()
                println("Ingrese los datos del individuo:")
                print("Nombre: ")
                val nombre = scanner.next()
                print("Edad: ")
                val edad = scanner.nextInt()
                print("Género (M/F): ")
                val genero = scanner.next()[0]
                print("Peso: ")
                val peso = scanner.nextDouble()
                print("Altura: ")
                val altura = scanner.nextDouble()
                val individuo = Individuo(nombre, edad, genero, peso, altura)
                gestorEspecies.createIndividuo(especieNombre, individuo)
            }
            6 -> {
                print("Ingrese el nombre del individuo a leer: ")
                val nombre: String = scannerString.nextLine()
                val individuo = gestorEspecies.readIndividuo(nombre)
                println(individuo ?: "Individuo no encontrado.")
            }
            7 -> {
                print("Ingrese el nombre del individuo a actualizar: ")
                val nombre = scannerString.nextLine()
                val individuo = gestorEspecies.readIndividuo(nombre)
                if (individuo != null) {
                    println("Ingrese los nuevos datos del individuo:")
                    print("Nombre: ")
                    individuo.nombre = scanner.next()
                    print("Edad: ")
                    individuo.edadP = scanner.nextInt()
                    print("Género (M/F): ")
                    individuo.genero = scanner.next()[0]
                    print("Peso: ")
                    individuo.pesoP = scanner.nextDouble()
                    print("Altura: ")
                    individuo.alturaP = scanner.nextDouble()
                    gestorEspecies.updateIndividuo(nombre, individuo)
                } else {
                    println("Individuo no encontrado.")
                }
            }
            8 -> {
                print("Ingrese el nombre del individuo a eliminar: ")
                val nombre = scannerString.nextLine()
                gestorEspecies.deleteIndividuo(nombre)
            }
            9 -> {
                val especies = gestorEspecies.listEspecies()
                if (especies.isEmpty()) {
                    println("No hay especies registradas.")
                } else {
                    println("Listado de Especies:")
                    especies.forEach { println(it) }
                }
            }
            10 -> {
                val individuos = gestorEspecies.listIndividuos()
                if (individuos.isEmpty()) {
                    println("No hay individuos registrados.")
                } else {
                    println("Listado de Individuos:")
                    individuos.forEach { println(it) }
                }
            }
            11 -> {
                println("Saliendo del programa.")
                return
            }
            else -> println("Opción no válida. Intente nuevamente.")
        }
    }
}
