package br.com.programweb.sorteioviga.usuario;

import br.com.programweb.sorteioviga.exception.NegocioException;
import br.com.programweb.sorteioviga.exception.RecursoDuplicadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // CREATE
    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        validarCamposUnicos(usuario);
        return usuarioRepository.save(usuario);
    }

    // READ (ALL)
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // READ (BY ID)
    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Usuário não encontrado com ID: " + id));
    }

    // UPDATE
    @Override
    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = buscarPorId(id); // Reusa o método buscarPorId para validar existência

        // Valida campos únicos (exceto para o próprio usuário)
        if (!usuarioExistente.getEmail().equals(usuario.getEmail()) && usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RecursoDuplicadoException("E-mail já cadastrado: " + usuario.getEmail());
        }
        if (!usuarioExistente.getCpf().equals(usuario.getCpf()) && usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new RecursoDuplicadoException("CPF já cadastrado: " + usuario.getCpf());
        }

        // Atualiza campos
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setCpf(usuario.getCpf());
        usuarioExistente.setDataNascimento(usuario.getDataNascimento());
        usuarioExistente.setTelefone(usuario.getTelefone());

        return usuarioRepository.save(usuarioExistente);
    }

    // DELETE
    @Override
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NegocioException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Método auxiliar para validação de campos únicos
    private void validarCamposUnicos(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RecursoDuplicadoException("E-mail já cadastrado: " + usuario.getEmail());
        }
        if (usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new RecursoDuplicadoException("CPF já cadastrado: " + usuario.getCpf());
        }
    }
}