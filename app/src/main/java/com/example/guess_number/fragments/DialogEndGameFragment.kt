package com.example.guess_number.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.guess_number.R

class LostDialogFragment(private val numeroGanador: Int, private val victoria: Boolean, private val reiniciarJuegoListener: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_dialog_end_game, null)

        val textTitle = view.findViewById<TextView>(R.id.playerStatus)
        //Comprueba si el jugador ha ganado o no para mostrar un mensaje u otro
        if(victoria){
            textTitle.text = getString(R.string.player_wins)
        }else{
            textTitle.text = getString(R.string.player_lose)
        }

        val textMessage = view.findViewById<TextView>(R.id.textMessage)
        textMessage.text = "El número ganador era:"

        val resultGame = view.findViewById<TextView>(R.id.ResultGame)
        resultGame.text = numeroGanador.toString()

        //El jugador pulsa el boton de SI => Se llama a la funcion para que se reinicie la partida
        val btnYes = view.findViewById<Button>(R.id.btnYes)
        btnYes.setOnClickListener {
            //Se reinicia la partida
            reiniciarJuegoListener()
            dismiss()
        }

        //El jugador pulsa el boton de NO => Se carga el fragmento del menu principal
        val btnNo = view.findViewById<Button>(R.id.btnNo)
        btnNo.setOnClickListener {
            //Envia al jugador al menu principal
            // Obtener el FragmentManager y comenzar una transacción para reemplazar el fragmento actual con el fragmento de inicio
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, MainMenuFragment()) // Reemplazar con el fragmento de inicio
            transaction.addToBackStack(null) // Agregar a la pila de retroceso si deseas permitir la navegación hacia atrás
            transaction.commit()
            dismiss()
        }

        builder.setView(view)
        return builder.create()
    }
}


