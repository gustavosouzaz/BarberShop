package com.example.barbershop.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.barbershop.R
import com.example.barbershop.databinding.ActivityAgendamentoBinding
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

                }
                hora < "8:00" && hora > "19:00" -> {

                }
                data.isEmpty() -> {

                }
               barbeiro1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {

               }
                barbeiro2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {

                }
                barbeiro3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {

                }
            }
        }


    }
}