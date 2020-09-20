package com.example.jahitin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.jahitin.R

class LoginFragment : Fragment() {
    private lateinit var buttonLogin : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonLogin = view.findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            Toast.makeText(activity, "LOGIN", Toast.LENGTH_SHORT).show()
            activity!!.finish()
        }
    }
}