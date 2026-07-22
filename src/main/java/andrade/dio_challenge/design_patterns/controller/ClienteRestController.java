package andrade.dio_challenge.design_patterns.controller;

import andrade.dio_challenge.design_patterns.model.entity.Cliente;
import andrade.dio_challenge.design_patterns.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<Iterable<Cliente>> buscarTodosClientes() {
        return ResponseEntity.ok(this.clienteRepository.findAll());
    }

    public ResponseEntity<Optional<Cliente>> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = Optional.of(clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado.")
        ));
        return ResponseEntity.ok(cliente);
    }

    //TO-DO: TERMINAR MÉTODOS DE BUSCA, ATUALIZAÇÃO E DELEÇÃO.


}
