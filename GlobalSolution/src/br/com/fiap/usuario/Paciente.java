package br.com.fiap.usuario;

import br.com.fiap.consulta.Consulta;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario {

    private String historicoSaudeMental;
    private boolean emCrise;
    private final List<Consulta> consultas = new ArrayList<>(); // Lista para armazenar as consultas associadas ao paciente
    private final List<String> historicoHumor = new ArrayList<>();
    
    public Paciente(String nome, String email, String cpf, String senha, String historicoSaudeMental) {
        super(nome, email, cpf, senha);
        this.historicoSaudeMental = historicoSaudeMental;
        this.emCrise = false;
    }

    public String getHistoricoSaudeMental() {
        return historicoSaudeMental;
    }

    public void setHistoricoSaudeMental(String historicoSaudeMental) {
        this.historicoSaudeMental = historicoSaudeMental;
    }

    public boolean isEmCrisis() {
        return emCrise;
    }

    public void setEmCrisis(boolean emCrise) {
        this.emCrise = emCrise;
    }

    public void atualizarHistoricoSaudeMental(String novoHistorico) {
        this.historicoSaudeMental = novoHistorico;
    }

    public void sinalizarCrise(boolean emCrise) {
        this.emCrise = emCrise;
    }
    
    public List<String> getHistoricoHumor() {
        return historicoHumor;
    }
    
    public void registrarHumor(String humor) {
        historicoHumor.add(humor);
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }
    
    

    public void agendarConsulta(Consulta consulta) {
        consultas.add(consulta);
        ProfissionalSaude profissionalSaude = consulta.getProfissionalSaude();
        profissionalSaude.ganharBonusConsulta(consulta);

        // Solicita o humor ao paciente durante o agendamento
        String humor = JOptionPane.showInputDialog("Como está seu humor hoje?");
        historicoHumor.add(humor);

        System.out.println("Consulta agendada para o paciente: " + getNome());
    }

    public void cancelarConsulta(Paciente paciente, Consulta consulta) {
        paciente.getConsultas().remove(consulta);
        System.out.println("Consulta cancelada pelo paciente: " + paciente.getNome());
    }

    public void listarHistoricoConsultas() {
        System.out.println("Histórico de consultas realizadas para o paciente " + getNome() + ":");
        for (Consulta consulta : consultas) {
            if (consulta.isRealizada()) {
                System.out.println(consulta);
            }
        }
    }

    public void listarConsultasAgendadas() {
        if (consultas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma consulta agendada.");
        } else {
            StringBuilder mensagem = new StringBuilder("Consultas Agendadas:\n");
            for (Consulta consulta : consultas) {
                mensagem.append(consulta.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.toString());
        }
    }

    public void listarHistoricoHumor() {
        System.out.println("Histórico de humor para o paciente " + getNome() + ":");
        for (String humor : historicoHumor) {
            System.out.println(humor);
        }
    }

    @Override
    public void excluirCadastro() {
        System.out.println("Excluindo o cadastro do paciente: " + getNome());

        // Cancelar todas as consultas associadas ao paciente
        for (Consulta consulta : consultas) {
            consulta.cancelarConsulta();
        }
        consultas.clear();
        System.out.println("Cadastro do paciente excluído com sucesso!");
    }

    @Override
    public boolean login(String senha, String email) {
        return super.login(senha, email);
    }

    @Override
    public String toString() {
        return "Paciente [nome=" + getNome() + ", email=" + getEmail() + ", cpf=" + getCpf() + ", historicoSaudeMental=" + historicoSaudeMental + "]";
    }

}
