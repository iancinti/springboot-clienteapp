package clienteapp.springbootclienteapp.controller;

import clienteapp.springbootclienteapp.models.entity.Ciudad;
import clienteapp.springbootclienteapp.models.entity.Cliente;
import clienteapp.springbootclienteapp.models.service.ICiudadService;
import clienteapp.springbootclienteapp.models.service.IClienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

    private final Logger logger = LogManager.getLogger(ClienteController.class);
    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ICiudadService ciudadService;

    @GetMapping
    public String listarClientes(Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Page<Cliente> clientePage = clienteService.getClientesPaginados(page, size);

        model.addAttribute("titulo", "Lista de Clientes");
        model.addAttribute("clientes", clientePage);

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

    @PostMapping("/save")
    public String guardar(@ModelAttribute Cliente cliente) {
        logger.info("Guardando cliente");
        clienteService.guardar(cliente);
        return "redirect:/views/clientes";
    }
}

