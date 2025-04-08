package br.com.programweb.sorteioviga.bilhete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BilheteService {

    @Autowired
    private BilheteRepository bilheteRepository;

    public List<Bilhete> listarTodos() {
        return bilheteRepository.findAll();
    }

    public Optional<Bilhete> buscarPorId(Long id) {
        return bilheteRepository.findById(id);
    }

    public Bilhete salvar(Bilhete bilhete) {
        return bilheteRepository.save(bilhete);
    }

    public Bilhete atualizar(Long id, Bilhete bilheteAtualizado) {
        return bilheteRepository.findById(id).map(bilhete -> {
            bilhete.setNumero(bilheteAtualizado.getNumero());
            bilhete.setUsuario(bilheteAtualizado.getUsuario());
            return bilheteRepository.save(bilhete);
        }).orElseThrow(() -> new RuntimeException("Bilhete não encontrado"));
    }

    public void deletar(Long id) {
        bilheteRepository.deleteById(id);
    }
}