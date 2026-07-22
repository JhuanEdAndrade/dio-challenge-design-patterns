package andrade.dio_challenge.design_patterns.model.repository;

import andrade.dio_challenge.design_patterns.model.entity.Carro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends CrudRepository<Carro, Long> {


}
