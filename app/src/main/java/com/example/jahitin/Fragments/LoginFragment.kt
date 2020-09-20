package com.example.jahitin.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.jahitin.BerandaActivity
import com.example.jahitin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var auth : FirebaseAuth
    private lateinit var email : EditText
    private lateinit var pass : EditText
    private lateinit var buttonLogin : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = Firebase.auth
        email = view.findViewById(R.id.inputEmail)
        pass = view.findViewById(R.id.inputPassword)

        buttonLogin = view.findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonLogin -> {
                Toast.makeText(activity, "Melakukan login...", Toast.LENGTH_LONG).show()
                auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(activity, BerandaActivity::class.java)
                            startActivity(intent)
                            activity!!.finish()
                        }
                        else {
                            Toast.makeText(activity, "Login gagal, periksa email atau password anda", Toast.LENGTH_LONG).show()
                            Log.w("Pesan", "Login gagal : ", task.exception)
                        }
                    }
            }
        }
    }
}