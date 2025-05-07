package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

 /*class TasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        // Configurar o adaptador do ViewPager2
        val adapter = TasksPagerAdapter(this)
        viewPager.adapter = adapter

        // Conectar TabLayout com ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) "Ativos" else "Cancelados"
        }.attach()
    }
}*/

/*class TasksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        val btnAtivos = findViewById<Button>(R.id.btnAtivos)
        val btnCancelados = findViewById<Button>(R.id.btnCancelados)

        btnAtivos.setOnClickListener {
            replaceFragment(ActiveFragment()) // Exibir agendamentos ativos
        }

        btnCancelados.setOnClickListener {
            replaceFragment(CancelledFragment()) // Exibir agendamentos cancelados
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}*/

class TasksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        val btnAtivos = findViewById<Button>(R.id.btnAtivos)

        btnAtivos.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ActiveFragment()) // Aqui carrega o fragmento
                .commit()
        }
    }
}




