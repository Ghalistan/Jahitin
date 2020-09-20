package com.example.jahitin.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.jahitin.AuthenticationActivity
import com.example.jahitin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AkunFragment : Fragment(), View.OnClickListener {
    private lateinit var auth : FirebaseAuth
    private lateinit var nama : TextView
    private lateinit var namaLengkap : TextView
    private lateinit var email : TextView
    private lateinit var alamat : TextView
    private lateinit var btnLogout : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nama = view.findViewById(R.id.nama)
        namaLengkap = view.findViewById(R.id.namaLengkap)
        email = view.findViewById(R.id.email)
        alamat = view.findViewById(R.id.alamat)
        btnLogout = view.findViewById(R.id.buttonLogout)

        auth = Firebase.auth
        val user = auth.currentUser!!
        nama.text = user.displayName
        namaLengkap.text = user.displayName
        email.text = user.email
        alamat.text = "Belum memasukkan alamat"
        btnLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        auth.signOut()
        val intent = Intent(activity, AuthenticationActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }
}