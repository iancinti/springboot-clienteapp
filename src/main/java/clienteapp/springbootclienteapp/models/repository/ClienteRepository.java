package clienteapp.springbootclienteapp.models.repository;

import clienteapp.springbootclienteapp.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
