package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private var tempoSaida: Long = 0
    private val tempoLimiteInatividade = 60_000L

    override fun onStop() {
        super.onStop()
        tempoSaida = System.currentTimeMillis()
    }

    override fun onResume() {
        super.onResume()
        val tempoAtual = System.currentTimeMillis()
        if (tempoSaida != 0L && (tempoAtual - tempoSaida > tempoLimiteInatividade)) {
            // Sessão expirada: volta ao login
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}