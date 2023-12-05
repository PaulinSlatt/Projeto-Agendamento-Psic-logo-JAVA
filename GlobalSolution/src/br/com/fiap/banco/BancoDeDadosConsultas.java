package br.com.fiap.banco;

import br.com.fiap.consulta.Consulta;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDadosConsultas {

    private static final List<Consulta> CONSULTAS = new ArrayList<>();

    public static List<Consulta> getConsultas() {
        return CONSULTAS;
    }

    public static void cadastrar(Consulta consulta) {
        CONSULTAS.add(consulta);
    }

    public static void excluirTodos() {
        CONSULTAS.clear();
    }

}
