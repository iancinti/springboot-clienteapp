package clienteapp.springbootclienteapp.models.service;

import clienteapp.springbootclienteapp.models.entity.Cliente;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IClienteService {

    public Iterable<Cliente> listarTodos();
    public void guardar(Cliente cliente);
    public Cliente buscarPorId(Long id);
    public void eliminar(Long id);
    Page<Cliente> getClientesPaginados(int page, int size);
    Page<Cliente> buscarClientesPorFiltro(String filtro, int page, int size);

    List<Cliente> getClientesPorIds(List<Long> selectedIdsList);

    Cliente getClienteDetailsById(Long clientId);
}
