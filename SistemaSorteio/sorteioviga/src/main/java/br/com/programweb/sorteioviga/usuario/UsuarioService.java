package br.com.programweb.sorteioviga.usuario;

import java.util.List;

public interface UsuarioService {

    // CREATE
    Usuario cadastrarUsuario(Usuario usuario);

    // READ
    List<Usuario> listarUsuarios();
    Usuario buscarPorId(Long id);

    // UPDATE
    Usuario atualizarUsuario(Long id, Usuario usuario);

    // DELETE
    void deletarUsuario(Long id);
}