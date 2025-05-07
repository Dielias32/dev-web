package com.example.myapplication


import android.content.Context;
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues


class DBHelper(context: Context) : SQLiteOpenHelper(context, "Usuarios.db", null, 6) {

override fun onCreate(db: SQLiteDatabase){
    db.execSQL(

        "CREATE TABLE usuarios (" +

                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT)"


        )

    val values = ContentValues().apply{
        put("username", "root")
        put("password", "root")
    }
    db.insert("usuarios", null, values)


    db.execSQL(

        """CREATE TABLE agendamentos(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL, 
                telefone TEXT NOT NULL, 
                cpf TEXT NOT NULL, 
                data TEXT NOT NULL,
                horario TEXT NOT NULL,
                obs TEXT NOT NULL)""".trimIndent()

    )




    }

    override fun onUpgrade(db: SQLiteDatabase, oldversion: Int, newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS agendamentos")

        onCreate(db)
    }

    fun verificarUsuario(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE username = ? AND password = ?",
            arrayOf(username,password)
        )
        val existe = cursor.count > 0
        cursor.close()
        return existe

    }



    fun adicionarUsuario(username: String, password: String): Boolean{
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("username", username)
            put("password", password)
        }

        val resultado = db.insert("usuarios", null, values)
        return resultado != -1L
    }

    fun inserirAgendamento(nome: String, telefone: String, cpf: String, data: String, horario: String, obs: String): Boolean {

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("nome", nome)
            put("telefone", telefone)
            put("cpf", cpf)
            put("data", data)
            put("horario", horario)
            put("obs", obs)
        }

        val resultado = db.insert("agendamentos", null, values)
        return resultado != -1L
    }

    fun listarAgendamentosAtivos(): List<Agendamento>{
        val lista = mutableListOf<Agendamento>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM agendamentos", null)

        if (cursor.moveToFirst()){
            do{
                val agendamento = Agendamento(
                    cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                    cursor.getString(cursor.getColumnIndexOrThrow("telefone")),
                    cursor.getString(cursor.getColumnIndexOrThrow("cpf")),
                    cursor.getString(cursor.getColumnIndexOrThrow("data")),
                    cursor.getString(cursor.getColumnIndexOrThrow("horario")),
                    cursor.getString(cursor.getColumnIndexOrThrow("obs"))
                )
                lista.add(agendamento)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lista
    }

}