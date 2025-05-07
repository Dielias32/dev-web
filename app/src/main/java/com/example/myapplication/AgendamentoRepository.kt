package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

object AgendamentoRepository {
    val agendamentosAtivos = mutableListOf<Agendamento>()
    val agendamentosCancelados = mutableListOf<Agendamento>()
}