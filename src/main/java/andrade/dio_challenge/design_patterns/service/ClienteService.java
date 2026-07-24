package andrade.dio_challenge.design_patterns.service;

import andrade.dio_challenge.design_patterns.model.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


public interface ClienteService {

    Cliente inserir(Cliente cliente);

    Cliente atualizarParcial(Long id, Map<String, Object> camposParaAtualizar);

    Cliente buscarClientePorNome(String nome);

    Iterable<Cliente> findAll();

    Cliente findById(Long id);

    Boolean existsById(Long id);

    void deleteById(Long id);
}
