package com.amog.moguelkrauseandrea_ejercicio01

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.amog.moguelkrauseandrea_ejercicio01.databinding.ActivityInputBinding
import java.util.*
import java.util.concurrent.TimeUnit

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private lateinit var date : Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)

        overridePendingTransition(com.google.android.material.R.anim.abc_fade_in, com.google.android.material.R.anim.abc_fade_out)

        setContentView(binding.root)

        // Iniciamos la fecha como la fecha de hoy
        date = Calendar.getInstance()
    }

    fun click(view: View) {

        // Click en el EditText de la fecha
        if (view.id == R.id.etDate) {
            // Inicializamos un nuevo diálogo DatePicker
            // En su listener establecemos el texto del EditText como la fecha elegida
            // Le enviamos la fecha de hoy para cuando se despliega
            val dpDialog = DatePickerDialog(
                this,
                R.style.SpinnerDatePickerStyle,
                {_, y, m, d ->
                    binding.etDate.setText(getString(R.string.dateText,d,m+1,y))
                    date.set(y,m,d)
                },
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH)
            )
            R.style.Theme_MoguelKrauseAndrea_Ejercicio01
            dpDialog.show()
        }
        // Click en el botón para enviar
        else if (view.id == R.id.btnGo) {
            // Revisamos y guardamos cada valor
            var correcto = true
            var nombre = ""
            var noCuenta = ""
            var email = ""

            // Nombre:
            if (binding.etName.text.isNotEmpty()) {
                nombre = binding.etName.text.toString()
            } else {
                binding.etName.error = getString(R.string.nameEmpty)
                correcto = false
            }

            // Fecha de nacimiento:
            if (binding.etDate.text.isNotEmpty()) {
                // Revisamos que el año se correcto (ie. edad entre 3 y 120 años)
                val fechaHoy = Calendar.getInstance()
                val dif = TimeUnit.DAYS.convert(fechaHoy.time.time - date.time.time, TimeUnit.MILLISECONDS) / 365
                if (dif > 120 || dif < 1) {
                    binding.etDate.error = getString(R.string.yearError)
                    correcto = false
                } else {
                    binding.etDate.error = null
                }
            } else {
                binding.etDate.error = getString(R.string.dateEmpty)
                correcto = false
            }

            // Número de cuenta
            if (binding.etNumber.text.isNotEmpty()) {
                if (binding.etNumber.text.length < 9) {
                    binding.etNumber.error = getString(R.string.numberError)
                    correcto = false
                } else {
                    noCuenta = binding.etNumber.text.toString()
                }
            } else {
                binding.etNumber.error = getString(R.string.numberEmpty)
                correcto = false
            }

            // Email:
            if (binding.etEmail.text.isNotEmpty()) {
                // Revisamos que tenga el formato correcto
                if (Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()) {
                    email = binding.etEmail.text.toString()
                } else {
                    binding.etEmail.error = getString(R.string.emailEmpty)
                    correcto = false
                }
            } else {
                binding.etEmail.error = getString(R.string.emailError)
                correcto = false
            }

            // Si todos los valores fueron correctos, enviamos a siguiente pantalla
            if (correcto) {
                val intent = Intent(this, DisplayActivity::class.java)
                val params = Bundle()
                params.putInt("YEAR", date.get(Calendar.YEAR))
                params.putInt("MONTH", date.get(Calendar.MONTH))
                params.putInt("DAY_OF_MONTH", date.get(Calendar.DAY_OF_MONTH))
                intent.putExtras(params)
                startActivity(intent)
            }
        }
    }
}