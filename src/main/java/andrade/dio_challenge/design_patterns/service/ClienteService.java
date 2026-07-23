package andrade.dio_challenge.design_patterns.service;

import andrade.dio_challenge.design_patterns.model.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface ClienteService {

    Cliente inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    Optional<Cliente> buscarClientePorNome(String nome);

    Iterable<Cliente> findAll();

    Cliente findById(Long id);

    Boolean existsById(Long id);

    void deleteById(Long id);
}
