package com.example.guess_number.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.guess_number.R

class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño XML del fragmento
        val view = inflater.inflate(R.layout.fragment_main_menu_application, container, false)

       //Botón de jugar
        val buttonJugar = view.findViewById<Button>(R.id.btnJugar)
        buttonJugar.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainMenuFragment_to_gameFragment)
        }

        //Botón de tutorial
        val buttonTutorial = view.findViewById<Button>(R.id.btnTutorial)
        buttonTutorial.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainMenuFragment_to_dialogTutorialFragment2)
        }

        view.findViewById<Button>(R.id.btnSalir).setOnClickListener {
            activity?.finish()  // Esto cierra la actividad actual y sale de la aplicación
        }

        return view
    }
}
