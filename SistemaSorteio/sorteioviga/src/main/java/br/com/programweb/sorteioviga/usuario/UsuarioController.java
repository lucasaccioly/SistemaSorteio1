package br.com.programweb.sorteioviga.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // CREATE
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(
            @RequestBody Usuario usuario,
            UriComponentsBuilder uriBuilder
    ) {
        Usuario usuarioSalvo = usuarioService.cadastrarUsuario(usuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioSalvo);
    }

    // READ (ALL)
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    // READ (BY ID)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuario
    ) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, usuario));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}