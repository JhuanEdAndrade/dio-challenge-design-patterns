package andrade.dio_challenge.design_patterns.service.implementation;

import andrade.dio_challenge.design_patterns.model.entity.Cliente;
import andrade.dio_challenge.design_patterns.model.repository.ClienteRepository;
import andrade.dio_challenge.design_patterns.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.Map;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente inserir(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizarParcial(Long id, Map<String, Object> camposParaAtualizar) {
        // 1. Busca o cliente atual no banco (intacto)
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // 2. Percorre apenas as propriedades que o usuário enviou no JSON
        camposParaAtualizar.forEach((nomeDaPropriedade, valorDaPropriedade) -> {

            // Busca se existe um atributo com esse nome na classe Cliente
            Field campo = ReflectionUtils.findField(Cliente.class, nomeDaPropriedade);

            if (campo != null) {
                // Remove a trava de segurança do "private" para podermos alterar o valor
                campo.setAccessible(true);
                // Atualiza o valor apenas desse campo específico no objeto clienteExistente
                ReflectionUtils.setField(campo, clienteExistente, valorDaPropriedade);
            }
        });

        // 3. Salva o cliente com os dados mesclados
        return clienteRepository.save(clienteExistente);
    }

    @Override
    public Cliente buscarClientePorNome(String name) {
        return clienteRepository.findByName(name);
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
