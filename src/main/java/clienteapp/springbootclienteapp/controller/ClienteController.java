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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(required = false) String filtro) {
        Page<Cliente> clientePage;

        if (filtro != null && !filtro.isEmpty()) {
            clientePage = clienteService.buscarClientesPorFiltro(filtro, page, size);
        } else {
            clientePage = clienteService.getClientesPaginados(page, size);
            filtro = "";
        }
        model.addAttribute("titulo", "Lista de Clientes");
        model.addAttribute("clientes", clientePage);
        model.addAttribute("filtro", filtro);

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

    @GetMapping("/search")
    public String buscarClientesFiltrados(Model model, @RequestParam String filtro,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Page<Cliente> clientePage = clienteService.buscarClientesPorFiltro(filtro, page, size);

        model.addAttribute("titulo", "Lista de Clientes (Filtrada)");
        model.addAttribute("clientes", clientePage);
        model.addAttribute("filtro", filtro);

        return "/views/clientes/resultados";
    }

    @GetMapping("/seleccionados")
    public String verSeleccionados(@RequestParam(name = "ids") String selectedIds, Model model) {
        List<Long> selectedIdsList = Arrays.stream(selectedIds.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<Cliente> selectedClientes = clienteService.getClientesPorIds(selectedIdsList);

        model.addAttribute("selectedClientes", selectedClientes);
        return "/views/clientes/seleccionados";
    }

}

