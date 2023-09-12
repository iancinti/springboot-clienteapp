package clienteapp.springbootclienteapp.models.service;

import clienteapp.springbootclienteapp.models.entity.Cliente;

public interface IClienteService {

    public List<Cliente> listarTodos();
    public void guardar(Cliente cliente);
    public Cliente buscarPorId(Long id);
    public void eliminar(Long id);

}
