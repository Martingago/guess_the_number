package com.example.guess_number.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
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
        view.findViewById<Button>(R.id.btnJugar).setOnClickListener {
            // Crear una instancia del fragmento de juego
            val gameFragment = GameFragment()

            // Obtener el FragmentManager y comenzar una transacción para reemplazar el fragmento actual con el fragmento de juego
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, gameFragment)
                .addToBackStack(null)
                .commit()
        }

        // Configurar el click listener para el botón "Tutorial"
        view.findViewById<Button>(R.id.btnTutorial).setOnClickListener {
            // Aquí puedes navegar a la pantalla de tutorial o ejecutar cualquier acción relacionada con "Tutorial"
        }

        view.findViewById<Button>(R.id.btnSalir).setOnClickListener {
            activity?.finish()  // Esto cierra la actividad actual y sale de la aplicación
        }

        return view
    }
}
