package com.example.jahitin.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jahitin.AuthenticationActivity
import com.example.jahitin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment(), View.OnClickListener {
    private lateinit var auth : FirebaseAuth
    private lateinit var nama : EditText
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var buttonRegis : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        nama = view.findViewById(R.id.inputNamaLengkap)
        email = view.findViewById(R.id.inputEmail)
        password = view.findViewById(R.id.inputPassword)
        buttonRegis = view.findViewById(R.id.buttonRegister)

        buttonRegis.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonRegister -> {
                if (!cekEmpty()) {
                    Log.d("Pesan", "GA KOSONG")
                }
            }
        }
    }

    fun cekEmpty() : Boolean {
        return if (nama.text.isEmpty()) {
            nama.error = "Masukkan nama lengkap anda"
            true
        }
        else if (email.text.isEmpty()) {
            email.error = "Masukkan email anda"
            true
        }
        else if (password.text.isEmpty() || password.text.length < 6) {
            password.error = "Password tidak boleh kosong atau kurang dari 6 huruf"
            true
        } else {
            false
        }
    }

    fun registAccount(email : String, pass : String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d("Pesan", "registerberhasil")
                } else {
                    Log.w("Pesan", "failure", task.exception)
                }
            }
    }
}