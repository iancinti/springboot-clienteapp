package clienteapp.springbootclienteapp.models.repository;

import clienteapp.springbootclienteapp.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
