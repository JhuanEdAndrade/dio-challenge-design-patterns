package andrade.dio_challenge.design_patterns.model.repository;

import andrade.dio_challenge.design_patterns.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Cliente findByName(String name);

}
