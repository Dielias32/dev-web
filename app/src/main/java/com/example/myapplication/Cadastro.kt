package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast

class CadastroActivity : BaseActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val etNovoUsuario = findViewById<EditText>(R.id.etNovoUsuario)
        val etNovaSenha = findViewById<EditText>(R.id.etNovaSenha)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        dbHelper = DBHelper(this)

        btnCadastrar.setOnClickListener {
            val usuario = etNovoUsuario.text.toString().trim()
            val senha = etNovaSenha.text.toString().trim()

            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else {
                val sucesso = dbHelper.adicionarUsuario(usuario, senha)
                if (sucesso) {
                    Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish() // Fecha a tela de cadastro
                } else {
                    Toast.makeText(this, "Erro ao cadastrar.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}