package br.com.fiap.consulta;

import java.time.LocalDateTime;
import java.util.Random;

import br.com.fiap.usuario.Paciente;
import br.com.fiap.usuario.ProfissionalSaude;

public class Consulta {

    private int idConsulta;
    private LocalDateTime dataHora;
    private String descricao;
    private boolean urgente;
    private boolean realizada;
    private Paciente paciente;  // Adicionado campo Paciente
    private ProfissionalSaude profissionalSaude;  // Adicionado campo ProfissionalSaude

    public Consulta(LocalDateTime dataHora, String descricao, boolean urgente, ProfissionalSaude profissionalSaude, Paciente paciente) {
        this.idConsulta = new Random().nextInt();
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.urgente = urgente;
        this.realizada = false;
        this.paciente = paciente;
        this.profissionalSaude = profissionalSaude;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ProfissionalSaude getProfissionalSaude() {
        return profissionalSaude;
    }

    public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
        this.profissionalSaude = profissionalSaude;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void marcarRealizada() {
        this.realizada = true;
    }

    public void cancelarConsulta() {
        if (!realizada) {
            System.out.println("Consulta cancelada com sucesso!");
        } else {
            System.out.println("Não é possível cancelar uma consulta já realizada.");
        }
    }

    @Override
    public String toString() {
        return "Consulta [idConsulta=" + idConsulta + ", dataHora=" + dataHora + ", descricao=" + descricao + ", urgente=" + urgente + ", realizada=" + realizada + "]";
    }
}

