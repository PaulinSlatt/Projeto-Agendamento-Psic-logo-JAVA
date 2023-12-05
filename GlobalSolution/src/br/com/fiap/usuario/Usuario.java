package br.com.fiap.usuario;

import java.util.Random;

public abstract class Usuario {

    protected int id;
    protected String nome;
    protected String email;
    protected String cpf;
    protected String senha;

    protected Usuario() {
    }

    protected Usuario(String nome, String email, String cpf, String senha) {
        this.id = new Random().nextInt();
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }

    protected Usuario(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public abstract void excluirCadastro();

    public boolean login(String senha, String email) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", email=" + email + ", cpf=" + cpf + "]";
    }

}
