package com.example.guess_number.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.guess_number.R
import androidx.core.content.ContextCompat

class GameFragment : Fragment(R.layout.fragment_main_game) {

    var result: Int = 0 //Numero aleatorio que el usuario debe adivinar
    var vidasRestantes: Int = 5 //número de vidas por defecto del jugador

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        // Inflar el diseño XML del fragmento
        val view = inflater.inflate(R.layout.fragment_main_game, container, false)

        val min = 1
        val max = 100
        result = getRandomNumber(min, max)

        val btnAdivinar = view.findViewById<Button>(R.id.btnUser)

        btnAdivinar.setOnClickListener {
            comprobarNumero(view)
        }

        return view
    }

    companion object {
        fun getRandomNumber(max: Int, min: Int): Int {
            return (Math.random() * (max - min) + min).toInt()
        }
    }

    fun comprobarNumero(view: View) {
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