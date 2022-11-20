package com.amog.moguelkrauseandrea_ejercicio01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.amog.moguelkrauseandrea_ejercicio01.databinding.ActivityDisplayBinding
import java.util.*
import java.util.concurrent.TimeUnit

class DisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)

        overridePendingTransition(com.google.android.material.R.anim.abc_fade_in, com.google.android.material.R.anim.abc_fade_out)

        setContentView(binding.root)

        // Recibimos los parámetros
        val bundle = intent.extras

        val year = bundle?.getInt("YEAR")
        val month = bundle?.getInt("MONTH")
        val day = bundle?.getInt("DAY_OF_MONTH")

        // Calculamos la edad
        val fechaNacimiento = Calendar.getInstance()
        if (year != null && month != null && day != null)
            fechaNacimiento.set(year, month, day)
        val fechaHoy = Calendar.getInstance()
        val edad = (TimeUnit.DAYS.convert(fechaHoy.time.time - fechaNacimiento.time.time, TimeUnit.MILLISECONDS) / 365).toInt()

        // Mostramos la edad
        binding.tvAge.setText(resources.getQuantityString(R.plurals.ageText, edad, edad))

        // Calculamos el signo zodiacal
        var zodiacIndex = 0
        var zodiacImg = R.drawable.libra
        when (fechaNacimiento.get(Calendar.MONTH)) {
            0   -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 19) 9 else 10
            1   -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 19) 10 else 11
            2   -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 20) 11 else 0
            3   -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 19) 0 else 1
            4   -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 20) 1 else 2
            5   -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 20) 2 else 3
            6   -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 22) 3 else 4
            7   -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 23) 4 else 5
            8   -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 22) 5 else 6
            9   -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 22) 6 else 7
            10  -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 21) 7 else 8
            11  -> zodiacIndex =  if (fechaNacimiento.get(Calendar.DAY_OF_MONTH) <= 21) 8 else 9
        }

        when (zodiacIndex) {
            0   -> zodiacImg = R.drawable.aries
            1   -> zodiacImg = R.drawable.tauro
            2   -> zodiacImg = R.drawable.gemini
            3   -> zodiacImg = R.drawable.cancer
            4   -> zodiacImg = R.drawable.leo
            5   -> zodiacImg = R.drawable.virgo
            6   -> zodiacImg = R.drawable.libra
            7   -> zodiacImg = R.drawable.scorpio
            8   -> zodiacImg = R.drawable.sagittarius
            9   -> zodiacImg = R.drawable.capricorn
            10  -> zodiacImg = R.drawable.aquarius
            11  -> zodiacImg = R.drawable.pisces
        }

        val zodiacArr = resources.getStringArray(R.array.zodiacSigns)

        // Mostramos el signo zodiacal
        binding.tvZodiacSign.setText(getString(R.string.zodiacText, zodiacArr[zodiacIndex]))
        binding.ivZodiacSign.setImageDrawable(getDrawable(zodiacImg))

        // Calculamos el signo del horóscopo chino
        val chineseIndex = fechaNacimiento.get(Calendar.YEAR).mod(12)
        val chineseArr = resources.getStringArray(R.array.chineseSigns)

        var chineseImg = R.drawable.monkey
        when (chineseIndex) {
            0   -> chineseImg = R.drawable.monkey
            1   -> chineseImg = R.drawable.rooster
            2   -> chineseImg = R.drawable.dog
            3   -> chineseImg = R.drawable.pig
            4   -> chineseImg = R.drawable.rat
            5   -> chineseImg = R.drawable.ox
            6   -> chineseImg = R.drawable.tiger
            7   -> chineseImg = R.drawable.rabbit
            8   -> chineseImg = R.drawable.dragon
            9   -> chineseImg = R.drawable.snake
            10  -> chineseImg = R.drawable.horse
            11  -> chineseImg = R.drawable.sheep
        }

        // Mostramos el signo chino
        binding.tvChineseSign.setText(getString(R.string.chineseText, chineseArr[chineseIndex]))
        binding.ivChineseSign.setImageDrawable(getDrawable(chineseImg))

    }
}