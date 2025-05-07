package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AgendamentoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agendamento, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val edtNome = view.findViewById<EditText>(R.id.edtNome)
        val edtTelefone = view.findViewById<EditText>(R.id.edtTelefone)
        val edtCpf = view.findViewById<EditText>(R.id.edtCpf)
        val edtData = view.findViewById<EditText>(R.id.edtData)
        val edtHorario = view.findViewById<EditText>(R.id.edtHorario)
        val edtObs = view.findViewById<EditText>(R.id.edtObs)
        val btnSalvar = view.findViewById<Button>(R.id.btnSalvar)


        edtHorario.isEnabled = false

        edtData.setOnClickListener {
            val calendario = java.util.Calendar.getInstance()
            val ano = calendario.get(java.util.Calendar.YEAR)
            val mes = calendario.get(java.util.Calendar.MONTH)
            val dia = calendario.get(java.util.Calendar.DAY_OF_MONTH)

            val dataPicker = android.app.DatePickerDialog(requireContext(), { _, selectedYear, selecteMonth, selectedDay ->

                val dataSelecionada = String.format("%02d/%02d/%04d", selectedDay, selecteMonth + 1, selectedYear)
                edtData.setText(dataSelecionada)
                edtHorario.isEnabled = true
            }, ano, mes, dia)
            dataPicker.show()
        }


        edtHorario.addTextChangedListener(object : android.text.TextWatcher{
            var isUpdating = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}


            override fun afterTextChanged(s: android.text.Editable) {

                if(isUpdating) return
                isUpdating = true

                val clean =s.toString().replace (":", "").take(4)
                val formatted = when{
                    clean.length >= 3 -> clean.substring(0, 2) + ":" + clean.substring(2)
                    clean.length >= 1 -> clean
                    else -> ""
                }
                edtHorario.setText(formatted)
                edtHorario.setSelection(formatted.length)
                isUpdating = false
            }

        })


        btnSalvar.setOnClickListener {
            val nome = edtNome.text.toString()
            val telefone = edtTelefone.text.toString()
            val cpf = edtCpf.text.toString()
            val data = edtData.text.toString()
            val horario = edtHorario.text.toString()
            val obs = edtObs.text.toString()

            val db = DBHelper(requireContext())
            val sucesso = db.inserirAgendamento(nome, telefone, cpf, data, horario, obs)

            /*if (nome.isNotEmpty() && telefone.isNotEmpty() && cpf.isNotEmpty() && horario.isNotEmpty()) {
                val novoAgendamento = Agendamento(nome, telefone, cpf, horario)
                AgendamentoRepository.agendamentosAtivos.add(novoAgendamento)

                Toast.makeText(requireContext(), "Agendamento salvo!", Toast.LENGTH_SHORT).show()

                // Limpar os campos após salvar
                edtNome.text.clear()
                edtTelefone.text.clear()
                edtCpf.text.clear()
                edtHorario.text.clear()
            } else {
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }*/

            if(sucesso){

                Toast.makeText(context, "Salvo", Toast.LENGTH_SHORT).show()
                edtNome.text.clear()
                edtTelefone.text.clear()
                edtCpf.text.clear()
                edtData.text.clear()
                edtHorario.text.clear()
                edtObs.text.clear()
            }else{
                Toast.makeText(context, "Erro ao slavar!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}