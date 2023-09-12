package clienteapp.springbootclienteapp.models.service;

import clienteapp.springbootclienteapp.models.entity.Cliente;

public interface IClienteService {

    public Iterable<Cliente> listarTodos();
    public void guardar(Cliente cliente);
    public Cliente buscarPorId(Long id);
    public void eliminar(Long id);

}
