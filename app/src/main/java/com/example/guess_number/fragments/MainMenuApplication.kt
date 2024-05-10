package com.example.guess_number.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.guess_number.R
import java.util.Locale

class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño XML del fragmento
        val view = inflater.inflate(R.layout.fragment_main_menu_application, container, false)

       //Imagen de portada en sus 2 idiomas
        val currentLocale = Locale.getDefault().language
        val imageView = view.findViewById<ImageView>(R.id.imageView)

        if (currentLocale == "es") {
            imageView.setImageResource(R.drawable.logotipo_adivina_el_numero_esp)
        } else {
            imageView.setImageResource(R.drawable.logotipo_adivina_el_numero_eng)
        }


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
