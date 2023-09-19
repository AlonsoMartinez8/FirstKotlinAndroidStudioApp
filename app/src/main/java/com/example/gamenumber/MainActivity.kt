package com.example.gamenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var tvintentos : TextView
    private lateinit var tvnumber : TextView
    private lateinit var tvmayormenor : TextView
    var numberazar = 0
    var number = 50
    var intentos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberazar = (1..100).random()

        tvintentos = findViewById<TextView>(R.id.tvintentos)
        tvnumber = findViewById<TextView>(R.id.tvnumber)
        tvmayormenor = findViewById<TextView>(R.id.tvmayormenor)

        val btnme1 = findViewById<Button>(R.id.btnme1)
        val btnme10 = findViewById<Button>(R.id.btnme10)
        val btnma10 = findViewById<Button>(R.id.btnma10)
        val btnma1 = findViewById<Button>(R.id.btnma1)

        btnme1.setOnClickListener{btnonclick(-1)}
        btnme10.setOnClickListener{btnonclick(-10)}
        btnma10.setOnClickListener{btnonclick(10)}
        btnma1.setOnClickListener{btnonclick(1)}
    }

    private fun btnonclick(i: Int) {
        number += i
        update()
    }

    private fun update() {
        tvnumber.text = "$number"
        intentos++
        tvintentos.text = "Intentos - $intentos"
        check()
    }

    private fun check() {
        if(number in 0..100){
            when {
                number > numberazar -> tvmayormenor.text = "Más bajo!!"
                number < numberazar -> tvmayormenor.text = "Más alto!!"
                number == numberazar -> {
                    tvmayormenor.text = "Correcto"
                    win()
                }
            }
        } else { notonrange() }
    }

    private fun win() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Enorabuena")
        builder.setMessage("Tardaste $intentos intentos en conseguirlo")
        builder.setPositiveButton("Volver a jugar") { _ , _ ->
            restart()
        }
        builder.setCancelable(false)
        builder.show()
    }

    private fun notonrange() {
        var builder2 = AlertDialog.Builder(this)
        builder2.setTitle("LO SENTIMOS!")
        builder2.setMessage("Number : $number, está fuera de rango (0-100)")
        builder2.setPositiveButton("Volver a jugar") { _ , _ ->
            restart()
        }
        builder2.setCancelable(false)
        builder2.show()
    }

    private fun restart() {
        numberazar = (1..100).random()
        number = 50
        intentos = 0
        tvmayormenor.text = ""
        tvnumber.text = "$number"
        tvintentos.text = "Intentos: $intentos"
    }
}