package br.com.programweb.sorteioviga.bilhete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bilhetes")
public class BilheteController {

    @Autowired
    private BilheteService bilheteService;

    @GetMapping
    public List<Bilhete> listarTodos() {
        return bilheteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Bilhete buscarPorId(@PathVariable Long id) {
        return bilheteService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Bilhete não encontrado"));
    }

    @PostMapping
    public Bilhete criar(@RequestBody Bilhete bilhete) {
        return bilheteService.salvar(bilhete);
    }

    @PutMapping("/{id}")
    public Bilhete atualizar(@PathVariable Long id, @RequestBody Bilhete bilhete) {
        return bilheteService.atualizar(id, bilhete);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        bilheteService.deletar(id);
    }
}