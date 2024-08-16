package com.example.a2024aswgr1daks

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Layout XML a usar
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged() // Actualizar la lista UI
        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)

        botonAnadirListView.setOnClickListener {
            anadirEntrenador(adaptador)
        }
        registerForContextMenu(listView) // Registrar el ListView para el menú contextual
    } // FINALIZA onCreate

    var positionItemSeleccionado = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos la lista de opciones
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtenemos la posición del elemento seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        // Obtenemos el objeto seleccionado
        positionItemSeleccionado = position
    } // FINALIZA onCreateContextMenu

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                mostrarSnackbar(
                    "Editar $positionItemSeleccionado")
                return true
            }
            R.id.mi_eliminar -> {
                mostrarSnackbar(
                    "Eliminar $positionItemSeleccionado")
                abrirDialogo() // NUEVA LÍNEA
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Estás seguro que deseas eliminar?")
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
            mostrarSnackbar("Aceptar $which")
        })
        builder.setNegativeButton("Cancelar", null)
        val opciones = resources.getStringArray(R.array.dias_semana)
        val seleccionPrevia = booleanArrayOf(
            true, // Lunes
            false, // Martes
            false, // Miércoles
            false, // Jueves
            false, // Viernes
            false, // Sábado
            false // Domingo
        )

        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            {
                dialog, which, isChecked -> mostrarSnackbar("Item $which")
            }
        )
        val dialogo = builder.create()
        dialogo.show()
    }

    fun anadirEntrenador(adaptador: ArrayAdapter<BEntrenador>){
        arreglo.add(BEntrenador(4, "Dawn", "Coordinadora Pokemon"))
        adaptador.notifyDataSetChanged() // Actualizar la lista UI
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_blist_view),
            texto,
            Snackbar.LENGTH_INDEFINITE
    )
        snack.show()
    }

}