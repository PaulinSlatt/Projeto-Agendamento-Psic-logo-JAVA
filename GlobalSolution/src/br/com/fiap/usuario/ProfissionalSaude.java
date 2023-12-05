package br.com.fiap.usuario;

import br.com.fiap.consulta.Consulta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalSaude extends Usuario {

    private final List<Consulta> consultas = new ArrayList<>();
    private double bonus; // Alteração do atributo
    private String crp;

    
    public ProfissionalSaude(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha);
        this.bonus = 0.0;
    }

    public ProfissionalSaude(String nome, String email, String cpf, String crp, double bonus) {
        super(nome, email, cpf);
        this.bonus = bonus;
        this.crp = crp;
    }

    public ProfissionalSaude(String nome, String email, String cpf, String senha, double bonus, String crp) {
        super(nome, email, cpf, senha);
        this.bonus = bonus;
        this.crp = crp;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    
    
    public String getCrp() {
    	return crp;
    }
    
    public ProfissionalSaude(String crp) {
		super();
		this.crp = crp;
	}

	public List<Consulta> getConsultas() {
        return consultas;
    }

    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
        System.out.println("Consulta adicionada ao profissional de saúde: " + getNome());
    }

    public void cancelarConsulta(Consulta consulta) {
        if (consultas.contains(consulta)) {
            consultas.remove(consulta);
            System.out.println("Consulta cancelada com sucesso!");
        } else {
            System.out.println("Consulta não encontrada para cancelamento.");
        }
    }

    public void realizarConsulta(Consulta consulta) {
        if (!consulta.isRealizada()) {
            consulta.marcarRealizada();
            bonus += 100.0; // Incrementando o bônus por consulta realizada
            System.out.println("Consulta realizada com sucesso! Bônus atual: R$" + bonus);
        } else {
            System.out.println("Consulta já realizada anteriormente.");
        }
    }

    public double calcularBonusTotal() {
        return bonus;
    }

    public void visualizarConsultasRealizadas() {
        for (Consulta consulta : consultas) {
            if (consulta.isRealizada()) {
                System.out.println(consulta);
            }
        }
    }

    public void listarConsultasAgendadas() {
        for (Consulta consulta : consultas) {
            System.out.println(consulta);
        }
    }

    public void visualizarConsultasUrgentes() {
        for (Consulta consulta : consultas) {
            if (consulta.isUrgente()) {
                System.out.println(consulta);
            }
        }
    }
    
    public void ganharBonusConsulta(Consulta consulta) {
        if (!consulta.isRealizada()) {
            consulta.marcarRealizada();
            bonus += 100.0;
            System.out.println("Consulta realizada com sucesso! Bônus atual: R$" + bonus);
        } else {
            System.out.println("Consulta já realizada anteriormente.");
        }
        }

    public boolean verificarDisponibilidadeHorario(LocalDateTime horario) {
        for (Consulta consulta : consultas) {
            if (consulta.getDataHora().equals(horario)) {
                return false; // Horário ocupado
            }
        }
        return true; // Horário disponível
    }
    
    @Override
    public void excluirCadastro() {
        
        System.out.println("Excluindo o cadastro do profissional de saúde: " + getNome());

        // Cancelar todas as consultas associadas ao profissional de saúde
        for (Consulta consulta : consultas) {
            consulta.cancelarConsulta();
        }
        consultas.clear();
        System.out.println("Cadastro do profissional de saúde excluído com sucesso!");
    }

    @Override
    public boolean login(String senha, String email) {
        return super.login(senha, email);
    }

    @Override
    public String toString() {
        return "ProfissionalSaude{" +
                "nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", crp='" + crp + '\'' +
                ", bonus=" + bonus +
                '}';
    }

}
