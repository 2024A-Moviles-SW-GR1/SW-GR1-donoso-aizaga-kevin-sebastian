package com.example.a2024aswgr1daks

class BBaseDatosMemoria {
    // Companion object para simular una base de datos
    companion object {
        // Función para obtener todos los entrenadores
        val arregloBEntrenador = arrayListOf<BEntrenador>()

        init {
            arregloBEntrenador.add(BEntrenador(1, "Ash", "Maestro Pokemon"))
            arregloBEntrenador.add(BEntrenador(2, "Misty", "Lider de Gimnasio"))
            arregloBEntrenador.add(BEntrenador(3, "Brock", "Criador de Pokemon"))
        }

        fun guardarEntrenador(entrenador: BEntrenador): ArrayList<BEntrenador> {
            arregloBEntrenador.add(entrenador)
            return arregloBEntrenador
        }

        fun eliminarEntrenador(entrenador: BEntrenador): ArrayList<BEntrenador> {
            arregloBEntrenador.remove(entrenador)
            return arregloBEntrenador
        }

        fun actualizarEntrenador(entrenador: BEntrenador): ArrayList<BEntrenador> {
            val posicion = arregloBEntrenador.indexOfFirst {
                it.id == entrenador.id
            }
            arregloBEntrenador[posicion] = entrenador
            return arregloBEntrenador
        }
    }

}