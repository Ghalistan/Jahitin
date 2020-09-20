package com.example.jahitin.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jahitin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
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
                    registAccount(email.text.toString(), password.text.toString())
                    Toast.makeText(activity, "Mengirimkan data...", Toast.LENGTH_LONG).show()
                    hideKeyboard()
                }
            }
        }
    }

    fun hideKeyboard() {
        val view = activity!!.currentFocus
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    private fun cekEmpty() : Boolean {
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

    private fun registAccount(e : String, p : String) {
        auth.createUserWithEmailAndPassword(e, p)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    val update = userProfileChangeRequest { displayName = nama.text.toString() }
                    user!!.updateProfile(update)
                        .addOnCompleteListener { task ->
                            Toast.makeText(activity, "Registrasi berhasil, silahkan login", Toast.LENGTH_LONG).show()
                            nama.text.clear()
                            email.text.clear()
                            password.text.clear()
                        }
                } else {
                    Toast.makeText(activity, "Registrasi gagal, coba lagi nanti", Toast.LENGTH_LONG).show()
                    Log.w("Pesan", "Register Failed : ", task.exception)
                }
            }
    }
}