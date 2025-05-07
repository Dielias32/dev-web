package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnEnter = findViewById<Button>(R.id.btnEnter)

        /*val btnEnter = findViewById<Button>(R.id.btnEnter)
        btnEnter.setOnClickListener {
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent)
        }*/

        btnEnter.setOnClickListener {
            val usuario = etUsername.text.toString().trim()
            val senha = etPassword.text.toString().trim()

            if(usuario.isEmpty() || senha.isEmpty()){
                Toast.makeText(this, "Informe todos os campos!", Toast.LENGTH_SHORT).show()
            }else{
                val valido = dbHelper.verificarUsuario(usuario, senha)
                if (valido){
                    Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ScheduleActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Usuario ou senha inválidos!", Toast.LENGTH_SHORT).show()
                }
            }

        }



        val linkCadastro = findViewById<TextView>(R.id.linkCadastro)
        linkCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}