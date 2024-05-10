package com.example.guess_number.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.guess_number.R

class TutorialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dialog_tutorial, container, false)

        //boton de volver al menu
        val buttonSalir = view.findViewById<Button>(R.id.btnCloseTutorial)
        buttonSalir.setOnClickListener {
            view.findNavController().navigate(R.id.action_dialogTutorialFragment_to_mainMenuFragment2)
        }

        return view
    }

}