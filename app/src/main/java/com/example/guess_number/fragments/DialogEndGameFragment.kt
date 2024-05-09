package com.example.guess_number.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.guess_number.R

class LostDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Construir el diálogo
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_dialog_end_game, null)

        // Se recogen los datos provenientes del fragmento
        val args = LostDialogFragmentArgs.fromBundle(requireArguments())
        val textTitle = view.findViewById<TextView>(R.id.playerStatus)

        // Comprueba si el jugador ha ganado o no para mostrar un mensaje u otro
        textTitle.text = if (args.victoria) {
            getString(R.string.player_wins)
        } else {
            getString(R.string.player_lose)
        }

        val textMessage = view.findViewById<TextView>(R.id.textMessage)
        textMessage.text = getString(R.string.subtitle_result)

        val resultGame = view.findViewById<TextView>(R.id.ResultGame)
        resultGame.text = args.numeroGanador.toString()


        // Configuración del botón para reiniciar el juego
        val btnReiniciarJuego = view.findViewById<Button>(R.id.btnRestartGame)
        btnReiniciarJuego.setOnClickListener {
            findNavController().navigate(R.id.action_lostDialogFragment_to_gameFragment2)
        }

        // Configuración del botón para salir al menú principal
        val btnSalirJuego = view.findViewById<Button>(R.id.btnQuitGame)
        btnSalirJuego.setOnClickListener {
            findNavController().navigate(R.id.action_lostDialogFragment_to_mainMenuFragment)
        }

        builder.setView(view)
        return builder.create()
    }
}



