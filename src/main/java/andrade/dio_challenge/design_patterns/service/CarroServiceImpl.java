package andrade.dio_challenge.design_patterns.service;

import andrade.dio_challenge.design_patterns.model.entity.Carro;
import andrade.dio_challenge.design_patterns.model.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroServiceImpl implements CarroService{

    private final CarroRepository carroRepository;

    public CarroServiceImpl(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @Override
    public List<Carro> findByModelo(String modelo) {
        return carroRepository.findByModelo(modelo);
        }

    @Override
    public void deleteByModelo(String modelo) {

    }

    @Override
    public Carro save(Carro carro) {
        return carroRepository.save(carro);
    }

    @Override
    public Iterable<Carro> findAll() {
        return carroRepository.findAll();
    }
}
