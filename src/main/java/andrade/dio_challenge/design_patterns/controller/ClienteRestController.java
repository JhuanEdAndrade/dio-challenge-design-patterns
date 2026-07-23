package andrade.dio_challenge.design_patterns.controller;

import andrade.dio_challenge.design_patterns.model.entity.Cliente;
import andrade.dio_challenge.design_patterns.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import andrade.dio_challenge.design_patterns.service.ClienteService;

import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private final ClienteService clienteService;

    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Cliente>> listarTodosClientes() {
        return ResponseEntity.ok(this.clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Optional<Cliente>> buscarClientePorNome(@PathVariable String nome) {
        return ResponseEntity.ok(clienteService.buscarClientePorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.inserir(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarClientePorId(@PathVariable Long id,
                                                         @RequestBody Cliente cliente){
        var verificarClienteExistente = this.clienteService.existsById(id);
        if(verificarClienteExistente){
            clienteService.atualizar(id, cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientePorId(@PathVariable Long id){
        this.clienteService.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
