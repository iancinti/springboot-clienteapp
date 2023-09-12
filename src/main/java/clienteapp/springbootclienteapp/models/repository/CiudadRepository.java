package clienteapp.springbootclienteapp.models.repository;

import clienteapp.springbootclienteapp.models.entity.Ciudad;
import org.springframework.data.repository.CrudRepository;

public interface CiudadRepository extends CrudRepository<Ciudad, Long> {

}
