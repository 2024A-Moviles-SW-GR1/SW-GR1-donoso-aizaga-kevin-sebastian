package com.example.a2024aswgr1daks

    import android.app.Activity
    import android.content.Intent
    import android.net.Uri
    import android.os.Bundle
    import android.provider.ContactsContract
    import android.widget.Button
    import androidx.activity.result.contract.ActivityResultContracts
    import androidx.appcompat.app.AppCompatActivity
    import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_main),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    val callbackContenidoIntentExplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null) {
                // logica de negocio
                val data = result.data;
                mostrarSnackbar("${data?.getStringExtra("nombreModificado")}")
            }
        }
    }

    val callbackContenidoIntentImplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null) {
                // logica de negocio
                if (result.data!!.data != null) {
                    val uri: Uri = result.data!!.data!!
                    val cursor = contentResolver.query(uri, null, null, null, null)
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(indiceTelefono!!)
                    cursor?.close()
                    mostrarSnackbar("Telefono: $telefono")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Botón para ir a la actividad ACicloVida
        setContentView(R.layout.activity_main)
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida.setOnClickListener {
            irActividad(ACicloVida::class.java)
        }

        // Botón para ir a la actividad BListView
        val botonIrListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonIrListView.setOnClickListener {
            irActividad(BListView::class.java)
        }

        // Botón para ir a la actividad CIntentImplicito
        val botonIntentImplicito = findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )
        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            callbackContenidoIntentImplicito.launch(intentConRespuesta)
        }

        // Botón para ir a la actividad CIntentExplicitoParametros
        val botonIntentExplicito = findViewById<Button>(
            R.id.btn_ir_intent_explicito
        )
        botonIntentExplicito.setOnClickListener {
            val intentExplicito = Intent(
                this,
                CIntentExplicitoParametros::class.java
            )
            intentExplicito.putExtra("nombre", "Kevin")
            intentExplicito.putExtra("apellido", "Donoso")
            intentExplicito.putExtra("edad", 25)
            intentExplicito.putExtra(
                "entrenador",
                BEntrenador(1, "Kevin", "Donoso"))
            callbackContenidoIntentExplicito.launch(intentExplicito)
        }
    }

    // Función para ir a una actividad
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}