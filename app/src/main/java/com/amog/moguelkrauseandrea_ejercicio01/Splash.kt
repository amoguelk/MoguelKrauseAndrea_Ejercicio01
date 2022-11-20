package com.amog.moguelkrauseandrea_ejercicio01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amog.moguelkrauseandrea_ejercicio01.databinding.ActivitySplashBinding
import kotlin.concurrent.thread

class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        overridePendingTransition(com.google.android.material.R.anim.abc_fade_in, com.google.android.material.R.anim.abc_fade_out)

        setContentView(binding.root)

        thread {
            Thread.sleep(3000)
            startActivity(Intent(this, InputActivity::class.java))
            finish()
        }
    }
}