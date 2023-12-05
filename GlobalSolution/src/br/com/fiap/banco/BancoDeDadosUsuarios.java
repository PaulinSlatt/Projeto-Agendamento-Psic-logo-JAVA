package br.com.fiap.banco;

import br.com.fiap.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDadosUsuarios {

    private static final List<Usuario> USUARIOS = new ArrayList<>();

    public static List<Usuario> getUsuarios() {
        return USUARIOS;
    }

    public static boolean login(String senha, String email) {
        for (Usuario usuario : USUARIOS) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public static void cadastrar(Usuario usuario) {
        USUARIOS.add(usuario);
    }

    public static void excluirTodos() {
        USUARIOS.clear();
    }

}
