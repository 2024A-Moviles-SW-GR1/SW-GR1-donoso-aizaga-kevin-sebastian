package com.example.a2024aswgr1daks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {

    // Variable global para almacenar el texto
    var textoGlobal = ""

    // Función para mostrar un mensaje en un Snackbar
    fun mostrarSnackBar(texto: String){
        textoGlobal += texto + "\n"
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_main),
            textoGlobal,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    // Función que se ejecuta al crear la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        mostrarSnackBar("onCreate")

    }

    // Funciones que se ejecutan al cambiar el estado de la actividad
    override fun onStart() {
        super.onStart()
        mostrarSnackBar("onStart")
    }

    // Función que se ejecuta al resumir la actividad
    override fun onResume() {
        super.onResume()
        mostrarSnackBar("onResume")
    }

    // Función que se ejecuta al reiniciar la actividad
    override fun onRestart() {
        super.onRestart()
        mostrarSnackBar("onRestart")
    }

    // Función que se ejecuta al pausar la actividad
    override fun onPause() {
        super.onPause()
        mostrarSnackBar("onPause")
    }

    // Función que se ejecuta al detener la actividad
    override fun onStop() {
        super.onStop()
        mostrarSnackBar("onStop")
    }

    // Función que se ejecuta al destruir la actividad
    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackBar("onDestroy")
    }

    // SOLUCIÓN: GUARDAR Y RESTAURAR VARIABLES DE LA ACTIVIDAD
    // Función que se ejecuta al cambiar la configuración de la actividad
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // GUARDAR LAS VARIABLES DE LA ACTIVIDAD PRIMITIVAS
            putString("variableTextoGuardada", textoGlobal)
        }
        super.onSaveInstanceState(outState)
    }

    // Función que se ejecuta al restaurar la actividad
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // RESTAURAR LAS VARIABLES DE LA ACTIVIDAD PRIMITIVAS
        val textoRecuperadoDeVariables: String? = savedInstanceState.getString("variableTextoGuardada")
        if (textoRecuperadoDeVariables != null){
            mostrarSnackBar(textoRecuperadoDeVariables)
            textoGlobal = textoRecuperadoDeVariables
        }
    }
}