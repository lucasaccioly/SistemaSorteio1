package br.com.programweb.sorteioviga.sorteio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sorteios")
public class SorteioController {

    @Autowired
    private SorteioService sorteioService;

    @GetMapping
    public List<Sorteio> listarTodos() {
        return sorteioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Sorteio buscarPorId(@PathVariable Long id) {
        return sorteioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Sorteio não encontrado"));
    }

    @PostMapping
    public Sorteio criar(@RequestBody Sorteio sorteio) {
        return sorteioService.salvar(sorteio);
    }

    @PutMapping("/{id}")
    public Sorteio atualizar(@PathVariable Long id, @RequestBody Sorteio sorteio) {
        return sorteioService.atualizar(id, sorteio);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        sorteioService.deletar(id);
    }
}