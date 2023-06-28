package com.example.barbershop.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.barbershop.R
import com.example.barbershop.databinding.ActivityHomeBinding
import com.example.barbershop.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityHomeBinding.inflate(layoutInflater)
            setContentView(binding.root)
            supportActionBar?.hide()

            val nome = intent.extras?.getString("nome")

            binding.txtNomeUsuario.text = "Bem-Vindo(a), $nome"
        }
    }




