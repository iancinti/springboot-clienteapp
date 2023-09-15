package clienteapp.springbootclienteapp.models.service;
import clienteapp.springbootclienteapp.models.entity.Ciudad;
import clienteapp.springbootclienteapp.models.entity.Cliente;
import clienteapp.springbootclienteapp.models.repository.CiudadRepository;
import clienteapp.springbootclienteapp.models.repository.ClienteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final Logger logger = LogManager.getLogger(ClienteServiceImpl.class);
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public Iterable<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public void guardar(Cliente cliente) {
        logger.info("Comenzando a guardar cliente");
        Ciudad ciudad = ciudadRepository.findById(cliente.getCiudad().getId()).get();
        logger.info("Se busco la ciudad: " + ciudad);
        cliente.setCiudad(ciudad);
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

    @Override
    public Page<Cliente> getClientesPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return (Page<Cliente>) clienteRepository.findAll(pageable);
    }
}

