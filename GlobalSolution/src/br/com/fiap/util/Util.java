package br.com.fiap.util;

import br.com.fiap.consulta.Consulta;
import br.com.fiap.banco.BancoDeDadosConsultas;
import br.com.fiap.usuario.Paciente;
import br.com.fiap.usuario.ProfissionalSaude;
import br.com.fiap.usuario.Usuario;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Util {
    private static ProfissionalSaude profissionalSaude;
    private static List<Paciente> pacientes = new ArrayList<>();  // Lista para armazenar pacientes
    private static List<ProfissionalSaude> profissionaisSaude = new ArrayList<>();  // Lista para armazenar profissionais de saúde

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int opcao;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(gerarMenu()));
            if (opcao < 1 || opcao > 8) {
                JOptionPane.showMessageDialog(null, "Opção inválida");
            } else {
                switch (opcao) {
                    case 1:
                        cadastrar();
                        break;
                    case 2:
                        realizarLogin();
                        break;
                    case 3:
                        exibirInformacoes();
                        break;
                    case 4:
                        excluirCadastro();
                        break;
                    case 5:
                        excluirTodos();
                        break;
                    case 6:
                        listarConsultas();
                        break;
                    case 7:
                        agendarConsulta();
                        break;
                }
            }
        } while (opcao != 8);
    }

    private static void cadastrar() {
        String nome, email, senha;

        int tipoCadastro = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo de cadastro:\n1. Profissional de Saúde\n2. Paciente"));

        nome = JOptionPane.showInputDialog("Nome");
        email = JOptionPane.showInputDialog("Email");

        // Validar e-mail único para ambos profissionais e pacientes
        while (emailJaCadastrado(email, pacientes, profissionaisSaude)) {
            JOptionPane.showMessageDialog(null, "E-mail já cadastrado. Tente novamente.");
            email = JOptionPane.showInputDialog("Email");
        }

        senha = JOptionPane.showInputDialog("Senha");

        if (tipoCadastro == 1) { // Profissional de Saúde
            System.out.println("Cadastrando Profissional de Saúde...");
            String crp;
            do {
                crp = JOptionPane.showInputDialog("CRP do Profissional de Saúde (apenas números):");
            } while (!validarCPF(crp));

            ProfissionalSaude novoProfissionalSaude = new ProfissionalSaude(nome, email, senha, crp, 0.0);
            profissionaisSaude.add(novoProfissionalSaude);
            profissionalSaude = novoProfissionalSaude; // Adiciona o novo profissionalSaude
            System.out.println("Profissional de Saúde cadastrado com sucesso!");
        } else if (tipoCadastro == 2) { // Paciente
           

            String cpf;
            do {
                cpf = JOptionPane.showInputDialog("CPF do Paciente (apenas números):");
            } while (!validarCPF(cpf));

            pacientes.add(new Paciente(nome, email, senha, cpf, null));
        }

        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
    }



    private static boolean validarCPF(String cpf) {
        // Adicione a validação do CPF conforme necessário
        return true;
    }

    private static boolean emailJaCadastrado(String email, List<Paciente> pacientes, List<ProfissionalSaude> profissionaisSaude) {
        for (Paciente paciente : pacientes) {
            if (paciente.getEmail().equals(email)) {
                return true;
            }
        }

        for (ProfissionalSaude profissional : profissionaisSaude) {
            if (profissional.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }
    

    private static void realizarLogin() {
        String email = JOptionPane.showInputDialog("Digite seu email");
        String senha = JOptionPane.showInputDialog("Digite sua senha");

        boolean loginProfissionalSaude = false;
        boolean loginPaciente = false;

        for (ProfissionalSaude profissional : profissionaisSaude) {
            if (profissional.login(senha, email)) {
                loginProfissionalSaude = true;
                break;
            }
        }

        for (Paciente paciente : pacientes) {
            if (paciente.login(senha, email)) {
                loginPaciente = true;
                break;
            }
        }

        if (loginProfissionalSaude || loginPaciente) {
            JOptionPane.showMessageDialog(null, "Login bem-sucedido");
        } else {
            JOptionPane.showMessageDialog(null, "Email ou senha inválidos. Tente novamente.");
        }
    }

    private static void exibirInformacoes() {
        StringBuilder informacoes = new StringBuilder();

        if (profissionalSaude != null) {
            informacoes.append("Informações do Profissional de Saúde:\n");
            informacoes.append(profissionalSaude.toString()).append("\n");
            informacoes.append("Bônus acumulado: R$").append(profissionalSaude.getBonus()).append("\n");
            informacoes.append("Consultas agendadas:\n");
            if (!profissionalSaude.getConsultas().isEmpty()) {
                for (Consulta consulta : profissionalSaude.getConsultas()) {
                    informacoes.append(consulta.toString()).append("\n");
                }
            } else {
                informacoes.append("Nenhuma consulta agendada.\n");
            }
        } else {
            informacoes.append("Nenhum Profissional de Saúde cadastrado.\n");
        }

        if (!pacientes.isEmpty()) {
            informacoes.append("\nInformações dos Pacientes:\n");
            for (Paciente paciente : pacientes) {
                informacoes.append(paciente.toString()).append("\n");
                informacoes.append("Consultas agendadas:\n");
                if (!paciente.getConsultas().isEmpty()) {
                    for (Consulta consulta : paciente.getConsultas()) {
                        informacoes.append(consulta.toString()).append("\n");
                    }
                } else {
                    informacoes.append("Nenhuma consulta agendada.\n");
                }

                
                informacoes.append("Histórico de Humor:\n");
                if (!paciente.getHistoricoHumor().isEmpty()) {
                    for (String humor : paciente.getHistoricoHumor()) {
                        informacoes.append(humor).append("\n");
                    }
                } else {
                    informacoes.append("Nenhum registro de humor.\n");
                }
            }
        } else {
            informacoes.append("\nNenhum Paciente cadastrado.\n");
        }

        JOptionPane.showMessageDialog(null, informacoes.toString());
    }

    
    private static void excluirCadastro() {
        String emailExcluir = JOptionPane.showInputDialog("Digite o email da conta a ser excluída:");
        String senhaExcluir = JOptionPane.showInputDialog("Digite a senha da conta a ser excluída:");

        if (profissionalSaude != null && profissionalSaude.login(senhaExcluir, emailExcluir)) {
            excluirCadastro(profissionalSaude);
        } else {
            excluirCadastroPaciente(emailExcluir, senhaExcluir);
        }
    }

    private static void excluirCadastroPaciente(String email, String senha) {
        Paciente pacienteExcluir = null;
        for (Paciente paciente : pacientes) {
            if (paciente.login(senha, email)) {
                pacienteExcluir = paciente;
                break;
            }
        }

        if (pacienteExcluir != null) {
            excluirCadastro(pacienteExcluir);
        } else {
            JOptionPane.showMessageDialog(null, "Email ou senha incorretos. Exclusão falhou.");
        }
    }

    private static void excluirCadastro(Usuario usuario) {
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a conta?");

        if (confirmacao == JOptionPane.YES_OPTION) {
            usuario.excluirCadastro();
            JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso");
        }
    }

    private static void excluirTodos() {
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir todas as contas?");

        if (confirmacao == JOptionPane.YES_OPTION) {
            profissionalSaude = null;
            pacientes.clear();
            profissionaisSaude.clear(); // Limpar lista de profissionais de saúde
            JOptionPane.showMessageDialog(null, "Todas as contas foram excluídas com sucesso");
        }
    }

    private static Paciente selecionarPaciente(List<Paciente> pacientes) {
        String emailPaciente = JOptionPane.showInputDialog("Digite o email do paciente:");
        for (Paciente paciente : pacientes) {
            if (paciente.getEmail().equals(emailPaciente)) {
                return paciente;
            }
        }
        JOptionPane.showMessageDialog(null, "Paciente não encontrado. Tente novamente.");
        return null;
    }

    private static ProfissionalSaude selecionarProfissionalSaude(List<ProfissionalSaude> profissionaisSaude) {
        String nomeProfissional = JOptionPane.showInputDialog("Digite o nome do profissional de saúde:");
        for (ProfissionalSaude profissional : profissionaisSaude) {
            if (profissional.getNome().equals(nomeProfissional)) {
                return profissional;
            }
        }
        JOptionPane.showMessageDialog(null, "Profissional de saúde não encontrado. Tente novamente.");
        return null;
    }

    private static void registrarHumor(Paciente paciente) {
        String humor = JOptionPane.showInputDialog("Como está seu humor hoje?");
        paciente.registrarHumor(humor);
    }
    
    private static void agendarConsulta() {
        if (!pacientes.isEmpty() && profissionalSaude != null) {
            Paciente paciente = selecionarPaciente(pacientes);
            if (paciente != null) {
                ProfissionalSaude profissionalEscolhido = selecionarProfissionalSaude(profissionaisSaude);
                if (profissionalEscolhido != null) {
                    String tipoConsulta = JOptionPane.showInputDialog("Escolha o tipo de consulta:\n1. Normal\n2. Urgente");
                    boolean urgente = tipoConsulta.equals("2");

                    String horarioConsulta = JOptionPane.showInputDialog("Digite o horário da consulta (formato: yyyy-MM-dd HH:mm)");
                    LocalDateTime horario = LocalDateTime.parse(horarioConsulta, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    String descricaoConsulta = JOptionPane.showInputDialog("Digite uma descrição para a consulta:");

                    Consulta consulta = new Consulta(horario, descricaoConsulta, urgente, profissionalEscolhido, paciente);
                    paciente.agendarConsulta(consulta);
                    BancoDeDadosConsultas.cadastrar(consulta);

                    registrarHumor(paciente);

                    JOptionPane.showMessageDialog(null, "Consulta agendada com sucesso!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma conta cadastrada ou nenhum profissional de saúde disponível. Crie uma conta primeiro.");
        }
    }

    
    private static void listarConsultas() {
        String email = JOptionPane.showInputDialog("Digite o email do paciente:");
        Paciente paciente = buscarPacientePorEmail(email);

        if (paciente != null) {
            paciente.listarConsultasAgendadas();
        } else {
            JOptionPane.showMessageDialog(null, "Paciente não encontrado. Tente novamente.");
        }
    }

    private static Paciente buscarPacientePorEmail(String email) {
        for (Paciente paciente : pacientes) {
            if (paciente.getEmail().equals(email)) {
                return paciente;
            }
        }
        return null;
    }

    private static String gerarMenu() {
        return "Escolha uma opção:\n" +
                "1. Cadastrar\n" +
                "2. Realizar login\n" +
                "3. Exibir informações\n" +
                "4. Excluir cadastro\n" +
                "5. Excluir todos os cadastros\n" +
                "6. Listar consultas\n" +
                "7. Agendar consulta\n" +
                "8. Sair";
    }
}


