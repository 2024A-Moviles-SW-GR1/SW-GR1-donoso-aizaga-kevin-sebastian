import java.util.*

fun main() {
    println("Hola Mundo")

    // INMUTABLE (No se RE ASIGNA "=")
    val inmutable: String = "Kevin"
    // inmutable = "Carlos" // ERROR!

    // MUTABLES
    var mutable: String = "Pamela"
    mutable = "Carlos" //OK
    // VAL > VAR

    // Duck Typing
    val ejemploVariable = "Kevin Donoso"
    ejemploVariable.trim()
    val edadEjemplo: Int = 12
    // ejemploVariable = edadEjemplo // Error!

    // Variables Primitivas
    val nombreE: String = "Kevin Donoso"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    // Clases en Java
    val fechaNacimiento: Date = Date()

    // When (Switch)
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        "S" -> println("Soltero")
        ("C") -> println("Casado")
        "D" -> println("Divorciado")
        "V" -> println("Viudo")
        else -> println("No Encontrado")
    }

    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No" // if else en una sola linea

    calcularSueldo(10.00) // Por el orden de los parametros saber que es sueldo
    calcularSueldo(10.00, 15.00, 20.00) // Por el orden de los parametros
    // Named Parameters
    // calcularSueldo(sueldo, tasa, bonoEspecial)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 15.00)

    // Uso de clases
    val sumaUno = Suma(1, 2) // new Suma(1, 2) en Kotlin no es necesario "new"
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    // Arreglos
    // Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)

    // Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // FOR EACH => Itera sobre cada elemento del arreglo (Unit)
    val respuestaForEach: Unit = arregloDinamico.
    forEach { valorActual: Int ->
        println("Valor Actual: ${valorActual}");
    }

    arregloDinamico.forEach{ println("Valor Actual (it): ${it}")}

    // MAP => Itera sobre cada elemento del arreglo y modifica el arreglo
    val respuestaMap: List<Double> = arregloDinamico.map {
        valorActual: Int ->
        return@map valorActual.toDouble() + 100.00
    }

    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15.00 }
    println(respuestaMapDos)

    // Filter => Filtra el arreglo y devuelve un arreglo nuevo con los valores que cumplan la condicion
    val respuestaFilter: List<Int> = arregloDinamico.filter {
        valorActual: Int ->
        val mayoresACinco: Boolean = valorActual > 5
        return@filter mayoresACinco
    }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR -> ANY (Alguno cumple la condicion?)
    val respuestaAny: Boolean = arregloDinamico.any {
        valorActual: Int ->
        return@any valorActual > 5
    }
    println(respuestaAny)

    // And -> ALL (Todos cumplen la condicion?)
    val respuestaAll: Boolean = arregloDinamico.all {
        valorActual: Int ->
        return@all valorActual > 5
    }
    println(respuestaAll)

    // Reduce -> Acumula el resultado de una operacion

    // Valor acumulado = 0 (Siempre empieza en 0 en Kotlin)
    // [1, 2, 3, 4, 5] -> Acumular "SUMAR" estos valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico.reduce {
        acumulado: Int, valorActual: Int ->
        return@reduce acumulado + valorActual // ->Cambiar o usar la logica de negocio
    }

    println(respuestaReduce)

    // return@reduce acumulado + (itemCarrito.cantidad * itemCarrito.precioUnitario)




} // Termina funcion Main

// void -> Unit
fun imprimirNombre(nombre: String): Unit{ // No es necesario poner Unit
    println("Nombre: ${nombre}") // Template Strings
}

fun calcularSueldo(
    sueldo: Double, // Requeridos
    tasa: Double = 12.00, // Opcionales (Default)
    bonoEspecial: Double? = null // Opcionales (Nullable)
    // Variable? -> "?" Es Nullable (puede en algun momento ser nulo)
): Double {
    // Int -> Int? (Nullable)
    // String -> String? (Nullable)
    // Date -> Date? (Nullable)
    if (bonoEspecial == null){
        return sueldo * (100/tasa)
    } else {
        return sueldo * (100/tasa) * bonoEspecial
    }
}

    // Clase escrita en Java
    abstract class NumerosJava {
        protected val numeroUno: Int
        private val numeroDos: Int

        constructor(
            uno: Int,
            dos: Int
        ) {
            this.numeroUno = uno
            this.numeroDos = dos
            println("Inicializar el objeto")
        }
    }

    // Clase escrita en Kotlin
    abstract class Numeros( // Constructor Primario
        // Caso 1) Parametro normal
        // uno:Int , (parametro (sin modificador acceso))

        // Caso 2) Parametro y propiedad (atributo) (protected)
        // private var uno: Int(propiedad "instancia.uno")
        protected var numeroUno: Int, //intsance.numeroUno
        protected var numeroDos: Int //intsance.numeroDos
    ) {
        init { // Bloque constructor primario OPCIONAL
            this.numeroUno
            this.numeroDos
            println("Inicializar el objeto")
        }
    }

    class Suma( // Constructor Secundario
        unoParametro: Int, // Parametro
        dosParametro: Int // Parametro
    ) : Numeros(// Clase Padre
        unoParametro, // Propiedad
        dosParametro // Propiedad
    ) {
        public val soyPublicoExplicito: String = "Explicito" // Publico
        val soyPublicoImplicito: String = "Implicito" // Publico (propiedades, metodos)

        init { // Bloque Codigo constructor primario
            this.numeroUno
            this.numeroDos

            numeroUno // this.OPCIONAL (propiedades, metodos)
            numeroDos // this.OPCIONAL (propiedades, metodos)

            this.soyPublicoExplicito
            this.soyPublicoImplicito // this.OPCIONAL (propiedades, metodos)
        }


    // Creamos multiples constructores para no tener que cambiar el codigo
    constructor( // Constructor Secundario
        uno:Int?,
        dos:Int
    ):this(
        if(uno == null) 0 else uno,
        dos
    )

    constructor( // Constructor Terceario
        uno: Int,
        dos: Int?
    ):this(
        uno,
        if(dos == null) 0 else dos,
    )

    constructor( // Constructor Cuarto
        uno: Int?,
        dos: Int?
    ):this(
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos,
    )

    // public fun sumar(): Int{} // Publico
    fun sumar(): Int { // Publico
        val total = numeroUno + numeroDos
        // Suma.agregarHistorial(total)
        // ("Suma." o "NombreClase.") es OPCIONAL)
        agregarHistorial(total)
        return total
    }

    // public fun restar(): Int{} // Publico
    companion object { // Comparte entre todas las instancias
        // similiar a static en Java
        // funciones y variables estaticas
        val pi = 3.1416 // Suma.pi

        fun elevarAlCuadrado(num: Int): Int{ // Suma.elevarAlCuadrado(2)
            return num * num
        }

        val historialSumas = arrayListOf<Int>() // Suma.historialSumas

        fun agregarHistorial(valorTotalSuma:Int){ // Suma.agregarHistorial(2)
            historialSumas.add(valorTotalSuma)
        }
    }
} // Termina Clase Suma







