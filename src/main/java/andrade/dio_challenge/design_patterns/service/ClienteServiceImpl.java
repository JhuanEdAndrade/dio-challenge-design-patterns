package andrade.dio_challenge.design_patterns.service;

import andrade.dio_challenge.design_patterns.model.entity.Cliente;
import andrade.dio_challenge.design_patterns.model.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente inserir(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        clienteRepository.findById(id).ifPresentOrElse(
                clienteExistente -> {
                    clienteRepository.save(cliente);
                },
                () -> {
                    throw new RuntimeException("Cliente não encontrado para o ID: " + id);
                }
        );
    }

    @Override
    public Optional<Cliente> buscarClientePorNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Override
    public Iterable<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    @Override
    public Boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
