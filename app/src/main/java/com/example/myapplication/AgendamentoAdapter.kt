package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AgendamentoAdapter(private val listaAgendamentos: List<Agendamento>) :
    RecyclerView.Adapter<AgendamentoAdapter.ViewHolder>() {

    /*class AgendamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNome: TextView = itemView.findViewById(R.id.txtNome)
        val txtTelefone: TextView = itemView.findViewById(R.id.txtTelefone)
        val txtCpf: TextView = itemView.findViewById(R.id.txtCpf)
        val txtHorario: TextView = itemView.findViewById(R.id.txtHorario)
    }*/


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtNome: TextView = itemView.findViewById(R.id.txtNome)
        val txtTelefone: TextView = itemView.findViewById(R.id.txtTelefone)
        val txtCpf: TextView = itemView.findViewById(R.id.txtCpf)
        val txtData: TextView = itemView.findViewById(R.id.txtData)
        val txtHorario: TextView = itemView.findViewById(R.id.txtHorario)
        val txtObs: TextView = itemView.findViewById(R.id.txtObs)
    }

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendamentoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_agendamento, parent, false)
        return AgendamentoViewHolder(view)
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_agendamento, parent, false)
        return ViewHolder(itemView)
    }

    /*override fun onBindViewHolder(holder: AgendamentoViewHolder, position: Int) {
        val agendamento = listaAgendamentos[position]
        holder.txtNome.text = agendamento.nome
        holder.txtTelefone.text = agendamento.telefone
        holder.txtCpf.text = agendamento.cpf
        holder.txtHorario.text = agendamento.horario
    }*/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val agendamento = listaAgendamentos[position]
        holder.txtNome.text = agendamento.nome
        holder.txtTelefone.text = agendamento.telefone
        holder.txtCpf.text = agendamento.cpf
        holder.txtData.text = agendamento.data
        holder.txtHorario.text = agendamento.horario
        holder.txtObs.text = agendamento.obs
    }

    /*override fun getItemCount(): Int {
        return listaAgendamentos.size
    }*/

    override fun getItemCount() = listaAgendamentos.size
}