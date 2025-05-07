package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class ScheduleActivity : BaseActivity() {

    private var tempoSaida: Long = 0
    private val tempoLimiteInat = 60_000L



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val btnAgendar = findViewById<Button>(R.id.btnAgendar)
        val btnVisualizar = findViewById<Button>(R.id.btnVisualizar)

        btnAgendar.setOnClickListener {
            replaceFragment(AgendamentoFragment())
        }

        btnVisualizar.setOnClickListener {
            val intent = Intent(this, TasksActivity::class.java)
            startActivity(intent) // Correção: Abrir a Activity corretamente
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }


    override fun onStop() {
        super.onStop()
        tempoSaida = System.currentTimeMillis()

    }


    override fun onResume(){
        super.onResume()
        val tempoAtual = System.currentTimeMillis()
        if(tempoSaida !=0L && (tempoSaida > tempoLimiteInat)){

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()

        }
    }
}