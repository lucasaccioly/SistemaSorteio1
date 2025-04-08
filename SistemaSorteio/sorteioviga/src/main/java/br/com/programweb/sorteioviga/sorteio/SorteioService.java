package br.com.programweb.sorteioviga.sorteio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SorteioService {

    @Autowired
    private SorteioRepository sorteioRepository;

    public List<Sorteio> listarTodos() {
        return sorteioRepository.findAll();
    }

    public Optional<Sorteio> buscarPorId(Long id) {
        return sorteioRepository.findById(id);
    }

    public Sorteio salvar(Sorteio sorteio) {
        return sorteioRepository.save(sorteio);
    }

    public Sorteio atualizar(Long id, Sorteio sorteioAtualizado) {
        return sorteioRepository.findById(id).map(sorteio -> {
            sorteio.setNome(sorteioAtualizado.getNome());
            sorteio.setData(sorteioAtualizado.getData());
            sorteio.setPremio(sorteioAtualizado.getPremio());
            return sorteioRepository.save(sorteio);
        }).orElseThrow(() -> new RuntimeException("Sorteio não encontrado"));
    }

    public void deletar(Long id) {
        sorteioRepository.deleteById(id);
    }
}
