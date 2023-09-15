package clienteapp.springbootclienteapp.models.service;

import clienteapp.springbootclienteapp.models.entity.Cliente;
import org.springframework.data.domain.Page;

public interface IClienteService {

    public Iterable<Cliente> listarTodos();
    public void guardar(Cliente cliente);
    public Cliente buscarPorId(Long id);
    public void eliminar(Long id);

    Page<Cliente> getClientesPaginados(int page, int size);
}
