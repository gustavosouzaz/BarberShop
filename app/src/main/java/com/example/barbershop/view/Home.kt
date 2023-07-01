package com.example.barbershop.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FillEventHistory
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.barbershop.R
import com.example.barbershop.adapter.ServicosAdapter
import com.example.barbershop.databinding.ActivityHomeBinding
import com.example.barbershop.databinding.ActivityMainBinding
import com.example.barbershop.model.Servicos
import com.google.android.material.snackbar.Snackbar

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private val listaServicos: MutableList<Servicos> = mutableListOf()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityHomeBinding.inflate(layoutInflater)
            setContentView(binding.root)
            supportActionBar?.hide()

            val nome = intent.extras?.getString("nome")

            binding.txtNomeUsuario.text = "Bem-Vindo(a), $nome"
            val recyclerViewServicos = binding.recyclerViewServicos
            recyclerViewServicos.layoutManager = GridLayoutManager(this, 2)
            servicosAdapter = ServicosAdapter(this, listaServicos)
            recyclerViewServicos.setHasFixedSize(true)
            recyclerViewServicos.adapter = servicosAdapter



        }

      private fun getServicos() {

          val servico1 = Servicos(R.drawable.baseline_arrow_forward_24,"Corte de Cabelo" )

      }

    }




