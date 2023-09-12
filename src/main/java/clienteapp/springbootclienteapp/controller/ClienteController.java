package clienteapp.springbootclienteapp.controller;

import clienteapp.springbootclienteapp.models.entity.Ciudad;
import clienteapp.springbootclienteapp.models.entity.Cliente;
import clienteapp.springbootclienteapp.models.service.ICiudadService;
import clienteapp.springbootclienteapp.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ICiudadService ciudadService;

    @GetMapping("/")
    public String listarClientes(Model model) {
        List<Cliente> listadoClientes = (List<Cliente>) clienteService.listarTodos();

        model.addAttribute("titulo", "Lista de Clientes");
        model.addAttribute("clientes", listadoClientes);
        return "/views/clientes/listar";
    }

    @GetMapping("/create")
    public String crear(Model model) {

        Cliente cliente = new Cliente();
        List<Ciudad> listCiudades = ciudadService.listaCiudades();

        model.addAttribute("titulo", "Formulario: Nuevo Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("ciudades", listCiudades);
        return "/views/clientes/frmCrear";
    }
}

