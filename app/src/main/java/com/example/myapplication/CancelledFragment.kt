package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CancelledFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AgendamentoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cancelled, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewCancelled)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        atualizarLista()
    }

    private fun atualizarLista() {
        adapter = AgendamentoAdapter(AgendamentoRepository.agendamentosCancelados)
        recyclerView.adapter = adapter
    }
}