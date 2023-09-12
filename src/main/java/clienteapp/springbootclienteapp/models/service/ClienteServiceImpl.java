package clienteapp.springbootclienteapp.models.service;
import clienteapp.springbootclienteapp.models.entity.Cliente;
import clienteapp.springbootclienteapp.models.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Iterable<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public void guardar (Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

}
