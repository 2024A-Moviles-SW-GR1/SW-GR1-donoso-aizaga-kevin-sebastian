package com.example.a2024aswgr1daks

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(R.layout.activity_cintent_explicito_parametros)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val entrenador = intent.getParcelableExtra<BEntrenador>("entrenador")
        val boton = findViewById<android.widget.Button>(R.id.btn_devolver_respuesta)
        boton.setOnClickListener {
            devolverRespuesta()
        }
    }

    fun devolverRespuesta(){
        val intentDevolverRespuesta = Intent() // Intent vacio
        // Agregar parametros al intent vacio que se va a devolver
        intentDevolverRespuesta.putExtra("nombreModificado", "Sebastian")
        // devolver mas parametros si queremos
        setResult(
            RESULT_OK, // Resultado que se envia a la actividad principal
            intentDevolverRespuesta
        )

        // Finalizar la actividad
        finish()

    }
}
