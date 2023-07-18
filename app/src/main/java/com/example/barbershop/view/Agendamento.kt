package com.example.barbershop.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi

import com.example.barbershop.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            

            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth) 
            
            var dia = dayOfMonth.toString()
            val mes: String
            
            if (dayOfMonth < 10){
                dia = "0$dayOfMonth"
            }
            if (monthOfYear < 10){
                mes = "" + (monthOfYear+1)
            }else{
                mes = (monthOfYear +1).toString()
            }
            
            data = "$dia / $mes / $year"
            
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val minuto: String

            if(minute < 10){
                minuto = "$minute"
            }else{
                minuto = minute.toString()
            }

            hora = "$hourOfDay:$minuto"

        }

        binding.timePicker.setIs24HourView(true)

        binding.btAgendar.setOnClickListener {

            val barbeiro1 = binding.barbeiro1
            val barbeiro2 = binding.barbeiro2
            val barbeiro3 = binding.barbeiro3


            when{
                hora.isEmpty() -> {
                     mensagem(it,"Preencha o horário!", cor = "#FF0000")
                }
                hora < "8:00" && hora > "19:00" -> {
                     mensagem(it,"Barber Shop esta fechado - horário de atendimento das 8:00 as 19:00",cor = "#FF0000")
                }
                data.isEmpty() -> {
                     mensagem(it,"Coloque uma data!", cor = "#FF0000")
                }
               barbeiro1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                  salvarAgendamento(it,nome,"Pedro Henrique",data,hora)
               }
                barbeiro2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it,nome,"Bernardo Martins",data,hora)
                }
                barbeiro3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it,nome,"Fernanda Silva",data,hora)
                }
                else -> {
                    mensagem(it,"Escolha um barbeiro!", cor = "#FF0000")
                }

            }
        }


    }
    private fun mensagem(view: View, mensagem: String, cor: String) {
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FFFFFF"))
        snackbar.show()

    }

    private fun salvarAgendamento(view: View, cliente: String, barbeiro: String, data: String, hora: String) {
        val db = FirebaseFirestore.getInstance()

        val dadoUsuario = hashMapOf(

            "cliente" to cliente,
            "barbeiro" to barbeiro,
            "data" to data,
            "hora" to hora
        )

        db.collection("agendamento").document(cliente).set(dadoUsuario).addOnCompleteListener {
            mensagem(view, "Agendamento realizado com sucesso!!","#FF03DAC5")
        }.addOnFailureListener {
            mensagem(view,"Erro no servidor!!", cor = "#FF0000")
        }

    }
}