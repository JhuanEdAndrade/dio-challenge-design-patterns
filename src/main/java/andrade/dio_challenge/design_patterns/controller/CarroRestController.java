package andrade.dio_challenge.design_patterns.controller;

import andrade.dio_challenge.design_patterns.model.entity.Carro;
import andrade.dio_challenge.design_patterns.model.repository.CarroRepository;
import andrade.dio_challenge.design_patterns.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes/carros")
public class CarroRestController{

    @Autowired
    private final CarroService carroService;

    public CarroRestController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Carro>> listarTodosCarros() {
        return ResponseEntity.ok(this.carroService.findAll());
    }

    @GetMapping("/{modelo}")
    public ResponseEntity<List<Carro>> buscarCarroPorModelo(@PathVariable String modelo) {
        return ResponseEntity.ok(carroService.findByModelo(modelo));

    }

    @PostMapping
    public ResponseEntity<Carro> inserirCarro(Carro carro) {
        return ResponseEntity.ok(this.carroService.save(carro));
    }

    @PutMapping("/{modelo}")
    public ResponseEntity<Carro> atualizarCarroPorId(@PathVariable String modelo,
                                                         @RequestBody Carro carro){
        var verificarCarroExistente = this.carroService.findByModelo(modelo)
                .stream()
                .anyMatch(c -> c.getModelo().equals(carro.getModelo()));
        if(verificarCarroExistente){
            return ResponseEntity.ok(this.carroService.save(carro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{modelo}")
    public ResponseEntity<Void> deleteCarroPorModelo(@PathVariable String modelo){
        this.carroService.deleteByModelo(modelo);
        return ResponseEntity.ok().build();
    }

}
