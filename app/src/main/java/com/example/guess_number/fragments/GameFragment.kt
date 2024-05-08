package com.example.guess_number.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.guess_number.R
import androidx.core.content.ContextCompat



class GameFragment : Fragment(R.layout.fragment_main_game) {

    var result: Int = 0
    var vidasRestantes: Int = 5

    companion object {
        fun getRandomNumber(max: Int, min: Int): Int {
            return (Math.random() * (max - min) + min).toInt()
        }
    }

    fun makeToast(str: String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
    }

    fun clickFunction(view: View) {
        val variable = view.findViewById<EditText>(R.id.editId)
        val userGuessing = variable.text.toString().toInt()
        val resultPlayer = view.findViewById<TextView>(R.id.resultPlayer)
        val vidasRestantesUI = view.findViewById<TextView>(R.id.TriesPlayer)
        val btnUser = view.findViewById<Button>(R.id.btnUser)

        when {
            userGuessing < result -> {
                resultPlayer.text = getString(R.string.higher_number)
                resultPlayer.setTextColor(ContextCompat.getColor(requireContext(), R.color.error))
                vidasRestantes--
            }
            userGuessing > result -> {
                resultPlayer.text = getString(R.string.lower_number)
                resultPlayer.setTextColor(ContextCompat.getColor(requireContext(), R.color.error))
                vidasRestantes--
            }
            else -> {
                resultPlayer.text = getString(R.string.correct_number)
                resultPlayer.setTextColor(ContextCompat.getColor(requireContext(), R.color.correct))
                btnUser.isEnabled = false
                mostrarDialogoPerdida(result, true) //El jugador ha ganado la partida
            }
        }
        vidasRestantesUI.text = vidasRestantes.toString()
        if(vidasRestantes == 0 && userGuessing != result){
            btnUser.isEnabled = false
            mostrarDialogoPerdida(result, false) //El jugador ha perdido
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val min = 1
        val max = 100
        result = getRandomNumber(min, max)

        view.findViewById<Button>(R.id.btnUser).setOnClickListener {
            clickFunction(view)
        }
    }


    //Funcion que muestra el dialogo de partida perdida.
    // Se le pasa como parametro el número ganador y una funcion lambda que se encarga de reiniciar la partida
    fun mostrarDialogoPerdida(numeroGanador: Int, victoria: Boolean) {
        val dialogFragment = LostDialogFragment(numeroGanador, victoria) {
            reiniciarJuego()
        }
        dialogFragment.show(parentFragmentManager, "LostDialogFragment")
    }

    // Funcion que reinicia el juego
    fun reiniciarJuego() {
        val min = 1
        val max = 100
        result = getRandomNumber(min, max)

        // Reinicia el número de vidas
        vidasRestantes = 5

        // Habilita el botón de usuario
        view?.findViewById<Button>(R.id.btnUser)?.isEnabled = true

        // Actualiza la interfaz de usuario (por ejemplo, limpiando el EditText y el TextView)
        view?.findViewById<EditText>(R.id.editId)?.text?.clear()
        view?.findViewById<TextView>(R.id.resultPlayer)?.text = ""
        view?.findViewById<TextView>(R.id.TriesPlayer)?.text = vidasRestantes.toString()
    }

}