package andrade.dio_challenge.design_patterns.service;

import andrade.dio_challenge.design_patterns.model.entity.Carro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CarroService {

    List<Carro> findByModelo(String modelo);

    void deleteByModelo(String modelo);

    Carro save(Carro carro);

    Iterable<Carro> findAll();
}
