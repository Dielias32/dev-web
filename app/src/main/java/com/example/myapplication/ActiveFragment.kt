package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActiveFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AgendamentoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_active, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerAgendamentos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        atualizarLista()
    }

    private fun atualizarLista() {
        val db = DBHelper(requireContext())
        val listaAgendamentos = db.listarAgendamentosAtivos()
        adapter = AgendamentoAdapter(listaAgendamentos)
        recyclerView.adapter = adapter
    }
}