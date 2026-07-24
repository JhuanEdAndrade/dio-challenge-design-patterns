package andrade.dio_challenge.design_patterns.controller;

import andrade.dio_challenge.design_patterns.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import andrade.dio_challenge.design_patterns.service.ClienteService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    @Autowired
    private final ClienteService clienteService;

    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Cliente>> listarTodosClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @GetMapping("/busca")
    public ResponseEntity<Cliente> buscarClientePorNome(@RequestParam String name) {
        return ResponseEntity.ok(clienteService.buscarClientePorNome(name));
    }

    @PostMapping
    public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente) {
        clienteService.inserir(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> atualizarClientePorId(@PathVariable Long id,
                                                         @RequestBody Map<String, Object> campos){
        Cliente clienteAtualizado = clienteService.atualizarParcial(id, campos);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientePorId(@PathVariable Long id){
        clienteService.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
